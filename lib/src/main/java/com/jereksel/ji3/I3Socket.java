package com.jereksel.ji3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.jereksel.ji3.I3SocketUtils.sendMessageAsync;
import static com.jereksel.ji3.I3SocketUtils.sendMessageSync;

public class I3Socket {

    private String socketLocation;
    private ObjectMapper mapper = new ObjectMapper();

    private I3Socket() {
        try {
            String[] command = {"i3", "--get-socketpath"};
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            socketLocation = in.readLine();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private I3Socket(String socketLocation) {
        this.socketLocation = socketLocation;
    }

    private static I3Socket i3Socket;

    public static synchronized I3Socket getSocket() {
        if (i3Socket == null) {
            i3Socket = new I3Socket();
        }
        return i3Socket;
    }

    public String getSocketLocation() {
        return socketLocation;
    }

    public I3Socket setSocketLocation(String socketLocation) {
        this.socketLocation = socketLocation;
        return this;
    }


    public List<Workspace> getWorkspaces() {
        String w = sendMessageSync(this, I3MessageType.GET_WORKSPACES, "");
        try {
            return mapper.readValue(w, new TypeReference<List<Workspace>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Output> getOutputs() {
        String w = sendMessageSync(this, I3MessageType.GET_OUTPUTS, "");
        try {
            return mapper.readValue(w, new TypeReference<List<Output>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Tree getTree() {
        String w = sendMessageSync(this, I3MessageType.GET_TREE, "");
        try {
            return mapper.readValue(w, Tree.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Version getVersion() {
        String w = sendMessageSync(this, I3MessageType.GET_VERSION, "");
        try {
            return mapper.readValue(w, Version.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void subscribeToWorkspace(Consumer<String> f) {
        sendMessageAsync(this, I3EventType.WORKSPACE, f);
    }

}
