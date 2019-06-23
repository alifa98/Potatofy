package gui.topPanels;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel(){
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth  = 3;
        gbc.gridheight = 1;
        SearchBox searchBox = new SearchBox();
        this.add(searchBox);

    }
}
