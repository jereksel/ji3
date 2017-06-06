package com.jereksel.ji3;

public enum  I3EventType {
    WORKSPACE("workspace"),
    OUTPUT("output"),
    MODE("mode"),
    WINDOW("window"),
    BARCONFIG_UPDATE("barconfig_update"),
    BINDING("binding");

    public final String type;

    I3EventType(String type) {
        this.type = type;
    }
}
