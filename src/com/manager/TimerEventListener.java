package com.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TimerEventListener implements ActionListener {

    private Manager manager;

    TimerEventListener(Manager manager){
        this.manager=manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        manager.intervalRun();
    }
}
