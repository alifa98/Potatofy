package gui.topPanels;

import com.Profile;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TopPanel extends JPanel {
    ProfilePanel profilePanel;

    public TopPanel() {

        //setting Border layout for top panel
        setLayout(new BorderLayout());


        //create and add profile panel to left side of top panel (WEST)
        profilePanel =  new ProfilePanel();
        add(profilePanel,BorderLayout.WEST);
    }

}

