package com;
import com.manager.Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager=new Manager();
        manager.initialSetting();
        manager.setEventListeners();
        manager.playTheHardCodedSong();

    }
}
