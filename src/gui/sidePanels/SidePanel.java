package gui.sidePanels;

import com.UserInfoCard;
import gui.CustomScrollBarUI;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialFonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class SidePanel extends JPanel {

    private ArrayList<FriendCard> cards; //This Arraylist keeps friendcards

    private JPanel mainCardContainer = new JPanel(); //this panel keeps cards.

    public SidePanel() {
        cards = new ArrayList<>();

        setBackground(new Color(255, 255, 255));

        //set layout for side panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        //Layout of main cards container and add scroll pane
        mainCardContainer.setLayout(new BoxLayout(mainCardContainer, BoxLayout.Y_AXIS));
        mainCardContainer.setAlignmentX(JPanel.LEFT_ALIGNMENT); // alignment of Components in BoxLayout should be the same to align really.
        JScrollPane mainScrollableCardPanel = new JScrollPane(mainCardContainer);
        mainScrollableCardPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollableCardPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollableCardPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());


        //Register Icon Package
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        //title For left side panel
        JLabel sideTitle = new JLabel("Friends Activity", SwingConstants.LEFT);
        sideTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT); // alignment of Components in BoxLayout should be the same to align really.
        Icon sideTitleIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.HISTORY, 20, new Color(0, 0, 0));
        sideTitle.setIcon(sideTitleIcon);
        sideTitle.setFont(MaterialFonts.BOLD);
        sideTitle.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5))); // Add Padding to title

        add(sideTitle);
        add(mainScrollableCardPanel);

        //set panel size equal to sidebar size
        Dimension panelSize = new Dimension(300, getPreferredSize().height);
        setPreferredSize(panelSize);
    }

    public void addCard(UserInfoCard info) {
        FriendCard newCard = new FriendCard(info);
        mainCardContainer.add(newCard,0);
        cards.add(newCard);
        validate();
    }
}
