package gui.topPanels;

import com.Profile;
import gui.ImageLabel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.utils.MaterialFonts;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfilePanel extends JPanel {
    ImageLabel image;
    JLabel nameLabel, settingIconLabel;

    public ProfilePanel() {

        //Padding to Profile Section
        Insets padding = new Insets(10, 20, 10, 10);
        setBorder(new EmptyBorder(padding));

        //padding prepare for labels
        Border paddingForElements = new EmptyBorder(new Insets(5, 5, 5, 5));


        //setting Grid Bag Layout to this Panel
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        setLayout(gridBagLayout);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        setAlignmentX(Panel.LEFT_ALIGNMENT);

        //setting avatar position and adding to panel
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        image = new ImageLabel(new ImageIcon("src\\gui\\icons\\png\\64\\default_avatar.png"));
        image.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagLayout.setConstraints(image, gridBagConstraints);
        add(image);

        //User name label setting and add
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx= 1;
        nameLabel = new JLabel("Guest", SwingConstants.LEFT);
        nameLabel.setFont(MaterialFonts.BOLD);
        gridBagLayout.setConstraints(nameLabel, gridBagConstraints);
        nameLabel.setBorder(paddingForElements);
        add(nameLabel);

        //setting icon position and add
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        Icon settingIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SETTINGS, 27, new Color(0, 0, 0));
        settingIconLabel = new JLabel(settingIcon, SwingConstants.LEFT);
        settingIconLabel.setBorder(paddingForElements);
        gridBagLayout.setConstraints(settingIconLabel, gridBagConstraints);
        add(settingIconLabel);


        //set panel size equal to sidebar size
        Dimension panelSize = new Dimension(300, getPreferredSize().height);
        setPreferredSize(panelSize);
    }

    public void setProfile(Profile profile) {
        //todo this method call in profile file checker if exist such file so calls this method ...
        nameLabel.setText(profile.getName());
        image.setIcon(new ImageIcon(profile.getAvatarFile().getPath()));
    }
}