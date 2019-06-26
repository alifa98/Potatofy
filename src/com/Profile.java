package com;

import media.music.Song;

import java.io.File;

/**
 * This Class saves User Profile Info for shows and
 */
public class Profile {
    private String name;
    private File avatarFile;

    private Profile(String name, File avatar){
        this.avatarFile = avatar;
        this.name = name;
    }

    public File getAvatarFile() {
        return avatarFile;
    }

    public String getName() {
        return name;
    }

    public void setAvatarFile(File avatarFile) {
        this.avatarFile = avatarFile;
    }

    public void setName(String name) {
        this.name = name;
    }
}
