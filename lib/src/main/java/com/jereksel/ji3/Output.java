package com.jereksel.ji3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Output {
    private String name;
    private boolean active;
    @JsonProperty("current_workspace")
    private String currentWorkspace;
    private Rectangle rect;

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public String getCurrentWorkspace() {
        return currentWorkspace;
    }

    public Rectangle getRect() {
        return rect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Output output = (Output) o;
        return active == output.active &&
                Objects.equals(name, output.name) &&
                Objects.equals(currentWorkspace, output.currentWorkspace) &&
                Objects.equals(rect, output.rect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, active, currentWorkspace, rect);
    }

    @Override
    public String toString() {
        return "Output{" +
                "name='" + name + '\'' +
                ", active=" + active +
                ", currentWorkspace='" + currentWorkspace + '\'' +
                ", rect=" + rect +
                '}';
    }
}
