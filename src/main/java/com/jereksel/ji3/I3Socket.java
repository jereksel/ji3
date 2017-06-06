package com.jereksel.ji3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class I3Socket {

    private String socketLocation;

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
}
