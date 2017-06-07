package com.jereksel.ji3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.Consumer;

import static com.jereksel.ji3.I3MessageType.SUBSCRIBE;

class I3SocketUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static List<Workspace> getWorkspaces(I3Socket socket) {
        String w = sendMessageSync(socket, I3MessageType.GET_WORKSPACES, "");
        try {
            return mapper.readValue(w, new TypeReference<List<Workspace>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static void runCommand(I3Socket socket, String command) {
        sendMessageSync(socket, I3MessageType.COMMAND, command);
    }


    static String sendMessageSync(I3Socket socket, I3MessageType messageType, String command) {

        try {
            try (AFUNIXSocket sock = AFUNIXSocket.newInstance()) {
                sock.connect(new AFUNIXSocketAddress(new File(socket.getSocketLocation())));

                ByteBuffer b = getByteBuffer(messageType, command);

                try (InputStream in = sock.getInputStream();
                     OutputStream os = sock.getOutputStream()) {
                    os.write(b.array());
                    os.flush();

                    return getString(in);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    static void sendMessageAsync(I3Socket socket, I3EventType type, Consumer<String> callback) {
        String command = "[" + "\"" + type.type + "\"" + "]";

        try {
            try (AFUNIXSocket sock = AFUNIXSocket.newInstance()) {
                sock.connect(new AFUNIXSocketAddress(new File(socket.getSocketLocation())));

                ByteBuffer b = getByteBuffer(SUBSCRIBE, command);

                try (InputStream in = sock.getInputStream();
                     OutputStream os = sock.getOutputStream()) {
                    os.write(b.array());
                    os.flush();

                    getString(in);

                    while (true) {
                        String s = getString(in);
                        callback.accept(s);
                    }
//                    return getString(in);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    private static String getString(InputStream in) throws IOException {
        byte[] y = new byte[6];
        int a = in.read(y);
        if (a == -1) {
            return "";
        }

        byte[] size = new byte[4];
        a = in.read(size);

        if (a < 4) {
            throw new RuntimeException("Error during size reading. Only " + a + " bytes read");
        }

        int messageSize = toInt(size);

//        System.out.println("Message size: " + messageSize);

        byte[] typeArr = new byte[4];
        in.read(typeArr);
        int type = toInt(typeArr);

        byte[] message = new byte[messageSize];
        int status = in.read(message);

        if (status == -1) {
            return null;
        }

        return new String(message);
    }

    private static ByteBuffer getByteBuffer(I3MessageType messageType, String command) {
        int length = command.length();


//                ByteBuffer b = ByteBuffer.wrap("i3-ipc".getBytes());

        return ByteBuffer.allocate(14 + length)
                .put(ByteBuffer.wrap("i3-ipc".getBytes()))
                .put(toByteArray(length))
                .put(toByteArray(messageType.id))
                .put(ByteBuffer.wrap(command.getBytes()));
    }

    private static byte[] toByteArray(int length) {
        int p = 256; //(int) Math.pow(2,8);

        byte l1 = (byte) (length % p);
        byte l2 = (byte) ((length >> 8) % p);
        byte l3 = (byte) ((length >> 16) % p);
        byte l4 = (byte) ((length >> 24) % p);

        return new byte[]{l1, l2, l3, l4};
    }

    private static int toInt(byte[] arr) {
        return (arr[0] & 0xFF) + ((arr[1] & 0xFF) << 8) + ((arr[2] & 0xFF) << 16) + ((arr[3] & 0xFF) << 24);
    }

    private class Pair<A,B> {

        private final A o1;
        private final B o2;

        public Pair(A o1, B o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

}
