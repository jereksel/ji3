package com.jereksel.ji3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tree {
    private int id;
    private String name;
    private String type;
    private List<Tree> nodes;
    private String border;
    @JsonProperty("current_border_width")
    private int currentBorderWidth;
    private String layout;
    private String orientation;
    private float percent;
    private Rectangle rect;
    @JsonProperty("window_rect")
    private Rectangle windowRect;
    @JsonProperty("deco_rect")
    private Rectangle decoRect;
    private Rectangle geometry;
    private int window;
    private boolean urgent;
    private boolean focused;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Tree> getNodes() {
        return nodes;
    }

    public String getBorder() {
        return border;
    }

    public int getCurrentBorderWidth() {
        return currentBorderWidth;
    }

    public String getLayout() {
        return layout;
    }

    public String getOrientation() {
        return orientation;
    }

    public float getPercent() {
        return percent;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Rectangle getWindowRect() {
        return windowRect;
    }

    public Rectangle getDecoRect() {
        return decoRect;
    }

    public Rectangle getGeometry() {
        return geometry;
    }

    public int getWindow() {
        return window;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public boolean isFocused() {
        return focused;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return id == tree.id &&
                currentBorderWidth == tree.currentBorderWidth &&
                Float.compare(tree.percent, percent) == 0 &&
                window == tree.window &&
                urgent == tree.urgent &&
                focused == tree.focused &&
                Objects.equals(name, tree.name) &&
                Objects.equals(type, tree.type) &&
                Objects.equals(nodes, tree.nodes) &&
                Objects.equals(border, tree.border) &&
                Objects.equals(layout, tree.layout) &&
                Objects.equals(orientation, tree.orientation) &&
                Objects.equals(rect, tree.rect) &&
                Objects.equals(windowRect, tree.windowRect) &&
                Objects.equals(decoRect, tree.decoRect) &&
                Objects.equals(geometry, tree.geometry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, nodes, border, currentBorderWidth, layout, orientation, percent, rect, windowRect, decoRect, geometry, window, urgent, focused);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", nodes=" + nodes +
                ", border='" + border + '\'' +
                ", currentBorderWidth=" + currentBorderWidth +
                ", layout='" + layout + '\'' +
                ", orientation='" + orientation + '\'' +
                ", percent=" + percent +
                ", rect=" + rect +
                ", windowRect=" + windowRect +
                ", decoRect=" + decoRect +
                ", geometry=" + geometry +
                ", window=" + window +
                ", urgent=" + urgent +
                ", focused=" + focused +
                '}';
    }
}
