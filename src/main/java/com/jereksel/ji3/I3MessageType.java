package com.jereksel.ji3;

public enum I3MessageType {

    COMMAND(0),
    GET_WORKSPACES(1),
    SUBSCRIBE(2),
    GET_OUTPUTS(3),
    GET_TREE(4),
    GET_MARKS(5),
    GET_BAR_CONFIG(6),
    GET_VERSION(7),
    GET_BINDING_MODES(8);

    public final int id;

    private I3MessageType(int id) {
        this.id = id;
    }

}
