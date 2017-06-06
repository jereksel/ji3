package com.jereksel.ji3;

public class Main {

    public static void main(String[] args) throws Exception {


        I3Socket socket = I3Socket.getSocket();


        String workspaces = I3SocketUtils.getWorkspaces(socket);

        System.out.println("Workspaces: " + workspaces);

//        I3SocketUtils.runCommand(socket, "restart");


//
//        File file = new File("/run/user/1000/i3/ipc-socket.1196");
//
//        try (AFUNIXSocket sock = AFUNIXSocket.newInstance()) {
//            try {
//                sock.connect(new AFUNIXSocketAddress(file));
//            } catch (AFUNIXSocketException e) {
//                System.out.println("Cannot connect to server. Have you started it?");
//                System.out.flush();
//                throw e;
//            }
//
//            ByteBuffer b = ByteBuffer.allocate(18+3)
//                    .put(ByteBuffer.wrap("i3-ipc".getBytes()))
//                    .put((byte) "restart".length())
//                    .put((byte) 0)
//                    .put((byte) 0)
//                    .put((byte) 0)
//                    .putInt(0)
//                    .put(ByteBuffer.wrap("restart".getBytes()));
//
//            try (InputStream is = sock.getInputStream(); //
//                 OutputStream os = sock.getOutputStream()) {
//
////                is.read();
//
//                os.write(b.array());
//
//            }


//        UnixSocketAddress address = new UnixSocketAddress(file);
//        UnixSocketChannel channel = UnixSocketChannel.open(address);
//        System.out.println("connected to " + channel.getRemoteSocketAddress());

//        InputStreamReader r = new InputStreamReader(Channels.newInputStream(channel));



//        }
//        System.out.println(b);
//        System.out.println(Arrays.toString(b.array()));
//        System.out.println(bytesToHex(b.array()));
//
//        PrintWriter w = new PrintWriter(Channels.newOutputStream(channel));
//        w.print(b);
//        w.flush();
//
//        Thread.sleep(5000);
//
//        InputStreamReader r = new InputStreamReader(Channels.newInputStream(channel));
//        CharBuffer result = CharBuffer.allocate(2);
//        r.read(result);
//        result.flip();
//        System.out.println("read from server: " + result.toString());


    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x ", b));
        }
        return builder.toString();
    }

}
