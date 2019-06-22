package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Panel topPanel, bottomPanel, sidePanel;
    public MainFrame(String title){
        super(title);
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =  (int) (screenSize.width * 0.8);
        int height =  (int) (screenSize.height * 0.75);
        this.setSize(width, height);

        //Setting Default Panels

    }

    public Panel getBottomPanel() {
        return bottomPanel;
    }

    public Panel getTopPanel() {
        return topPanel;
    }

    public Panel getSidePanel() {
        return sidePanel;
    }
}
