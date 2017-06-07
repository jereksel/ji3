package com.jereksel.ji3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Workspace {
    private int num;
    private String name;
    private boolean visible;
    private boolean focused;
    private boolean urgent;
    private Rectangle rect;
    private String output;

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isFocused() {
        return focused;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public Rectangle getRect() {
        return rect;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return num == workspace.num &&
                visible == workspace.visible &&
                focused == workspace.focused &&
                urgent == workspace.urgent &&
                Objects.equals(name, workspace.name) &&
                Objects.equals(rect, workspace.rect) &&
                Objects.equals(output, workspace.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, visible, focused, urgent, rect, output);
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", visible=" + visible +
                ", focused=" + focused +
                ", urgent=" + urgent +
                ", rect=" + rect +
                ", output='" + output + '\'' +
                '}';
    }
}
