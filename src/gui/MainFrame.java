package gui;

import gui.bottomPanels.BottomPanel;
import gui.mainPanels.SongsPanel;
import gui.sidePanels.SidePanel;
import gui.topPanels.TopPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private JPanel mainPanel;
    private SidePanel sidePanel;

    public MainFrame(String title){
        super(title);
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =  (int) (screenSize.width * 0.8);
        int height =  (int) (screenSize.height * 0.75);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(255,255,255));
        this.setLocationRelativeTo(null);
        //Setting Default Panels
        topPanel = new TopPanel();
        sidePanel = new SidePanel();
        mainPanel = new SongsPanel();
        bottomPanel = new BottomPanel();

        this.add(topPanel,BorderLayout.NORTH);
        this.add(sidePanel,BorderLayout.WEST);
        this.add(mainPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }
}
