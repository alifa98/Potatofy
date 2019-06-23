package com;
import java.io.File;

public class UserInfoCard {
    private File avatarSrc;
    private String friendName;
    private TimeData lastSeen;
    private String musicName;
    public UserInfoCard(File avatarSrc, String friendName, TimeData lastSeen, String musicName) {
        this.avatarSrc = avatarSrc;
        this.friendName = friendName;
        this.lastSeen = lastSeen;
        this.musicName = musicName;
    }

    public File getAvatarSrc() {
        return avatarSrc;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getMusicName() {
        return musicName;
    }

    public TimeData getLastSeen() {
        return lastSeen;
    }
}
