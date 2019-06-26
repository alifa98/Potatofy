package gui.topPanels;
import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    ProfilePanel profilePanel;
    SearchBox searchBox;
    RightButtons rightButtons;
    public TopPanel() {

        //setting Border layout for top panel
        setLayout(new BorderLayout());

        //create and add profile panel to left side of top panel (WEST)
        profilePanel =  new ProfilePanel();
        add(profilePanel,BorderLayout.WEST);

        //create and add search box panel to  middle of top panel (CENTER)
        searchBox = new SearchBox();
        add(searchBox, BorderLayout.CENTER);

        //create and add right button panel to  right of top panel (EAST)
        rightButtons = new RightButtons();
        add(rightButtons,BorderLayout.EAST);
    }

    public ProfilePanel getProfilePanel() {
        return profilePanel;
    }
}

