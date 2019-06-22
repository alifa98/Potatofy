package gui;

import gui.bottomPanels.BottomPanel;
import gui.mainPanels.SongsPanel;
import gui.sidePanels.SidePanel;
import gui.topPanels.TopPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Panel topPanel, bottomPanel, sidePanel, mainPanel;
    public MainFrame(String title){
        super(title);
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =  (int) (screenSize.width * 0.8);
        int height =  (int) (screenSize.height * 0.75);
        this.setSize(width, height);

        //Setting Default Panels
        topPanel = new TopPanel();
        sidePanel = new SidePanel();
        mainPanel = new SongsPanel();
        bottomPanel = new BottomPanel();
    }

    public Panel getBottomPanel() {
        return bottomPanel;
    }

    public void setMainPanel(Panel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Panel getTopPanel() {
        return topPanel;
    }

    public Panel getSidePanel() {
        return sidePanel;
    }
}
