package gui;

import gui.bottomPanels.BottomPanel;
import gui.mainPanels.*;
import gui.sidePanels.SidePanel;
import gui.topPanels.TopPanel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private SidePanel sidePanel;
    private JScrollPane currentMainPanel;


    public MainFrame(String title) {
        super(title);

        // Initialize the Look and feel to material design
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //todo call create  a songs panel and set so current main panel to add first time

        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1500; //we can use screen size
        int height = 810; //we can use screen size
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setLocationRelativeTo(null);

        //Register Icon Pack
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        //Setting Default Panels
        topPanel = new TopPanel();
        sidePanel = new SidePanel();
        bottomPanel = new BottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(currentMainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setMainPanel(JScrollPane mainPanel) {
        remove(currentMainPanel);
        add(mainPanel, BorderLayout.CENTER);
        currentMainPanel = mainPanel;
        repaint();
        System.out.println("Panel " + currentMainPanel + " setted to main"); //for debug

    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

}
