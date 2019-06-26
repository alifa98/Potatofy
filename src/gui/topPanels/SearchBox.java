package gui.topPanels;
import mdlaf.utils.MaterialFonts;

import javax.swing.*;
import java.awt.*;

public class SearchBox extends JPanel {
    private PlaceholderTextField textinput;
    private JButton searchButton;

    public SearchBox() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBagLayout);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx= 0.25; // added for stretch the j textfield to fill top
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        textinput = new PlaceholderTextField("");
        textinput.setColumns(20);
        textinput.setPlaceholder("Type some thing to search it . . .");
        textinput.setFont(MaterialFonts.MEDIUM);
        gridBagLayout.setConstraints(textinput, gridBagConstraints);
        add(textinput);

        //todo key up listener for call search shuld be added here.
    }
}
