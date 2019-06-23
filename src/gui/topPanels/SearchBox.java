package gui.topPanels;
import javax.swing.*;
import java.awt.*;

public class SearchBox extends JPanel {
    private JTextField textinput;
    private JButton searchButton;

    public SearchBox() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.gridwidth = 2;
//        gridBagConstraints.gridheight = 1;
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        textinput = new JTextField();
//        gridBagLayout.setConstraints(textinput, gridBagConstraints);
//        this.add(textinput);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        searchButton = new JButton(new ImageIcon("src//gui//icons//png//16//search.png"));
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setContentAreaFilled(false);
        gridBagLayout.setConstraints(searchButton,gridBagConstraints);
        this.add(searchButton);


    }
}
