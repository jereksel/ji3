package com.jereksel.ji3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Version {
    private int major;
    private int minor;
    private int patch;
    @JsonProperty("human_readable")
    private String humanReadable;
    @JsonProperty("loaded_config_file_name")
    private String loadedConfigFileName;

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public String getHumanReadable() {
        return humanReadable;
    }

    public String getLoadedConfigFileName() {
        return loadedConfigFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return major == version.major &&
                minor == version.minor &&
                patch == version.patch &&
                Objects.equals(humanReadable, version.humanReadable) &&
                Objects.equals(loadedConfigFileName, version.loadedConfigFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch, humanReadable, loadedConfigFileName);
    }

    @Override
    public String toString() {
        return "Version{" +
                "major=" + major +
                ", minor=" + minor +
                ", patch=" + patch +
                ", humanReadable='" + humanReadable + '\'' +
                ", loadedConfigFileName='" + loadedConfigFileName + '\'' +
                '}';
    }
}
