package gui.sidePanels;

import com.TimeData;
import com.UserInfoCard;
import gui.CustomColors;
import gui.ImageLabel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFonts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FriendCard extends JPanel {

    public FriendCard(UserInfoCard userInfo) {

        //padding to Friend Card
        Insets padding  = new Insets(10,20,10,10);
        this.setBorder(new EmptyBorder(padding));

        //padding prepare for labels
        Border paddingForElements = new EmptyBorder(new Insets(5,5,5,5));

        setBackground(CustomColors.PRIMARY_BRIGHTER);
        addMouseListener(MaterialUIMovement.getMovement(this,CustomColors.PRIMARY_BRIGHT));

        //create and set grid bag layout to card
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        ImageLabel image = new ImageLabel(new ImageIcon(userInfo.getAvatarSrc().getPath()));
        gridBagLayout.setConstraints(image, gridBagConstraints);
        this.add(image);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        JLabel friendNameLabel = new JLabel(userInfo.getFriendName());
        friendNameLabel.setFont(MaterialFonts.BLACK);
        friendNameLabel.setBorder(paddingForElements);
        gridBagLayout.setConstraints(friendNameLabel, gridBagConstraints);
        this.add(friendNameLabel);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel timeLabel = new JLabel(userInfo.getLastSeen().getRelativeTime());
        timeLabel.setFont(MaterialFonts.REGULAR);
        Icon timeIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ACCESS_TIME, 20, new Color(0, 0, 0));
        timeLabel.setIcon(timeIcon);
        timeLabel.setBorder(paddingForElements);
        gridBagLayout.setConstraints(timeLabel, gridBagConstraints);
        this.add(timeLabel);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        JLabel musicNameLabel = new JLabel(userInfo.getMusicName());
        musicNameLabel.setFont(MaterialFonts.ITALIC);
        musicNameLabel.setFont(MaterialFonts.REGULAR);
        Icon musicIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MUSIC_NOTE, 20, new Color(0, 0, 0));
        musicNameLabel.setIcon(musicIcon);
        musicNameLabel.setBorder(paddingForElements);
        gridBagLayout.setConstraints(musicNameLabel, gridBagConstraints);
        this.add(musicNameLabel);

        //BoxLayout is one of the few layout managers that respects the minimum and maximum sizes of a component.
        //so following code prevent a panel from stretching
        setMaximumSize(this.getPreferredSize());
    }

//    public static void main(String[] args) {
//        long d  = new Date().getTime() - (60*60*1000 + 2) ;
//        FriendCard c = new FriendCard(new UserInfoCard(
//                new File("src\\gui\\icons\\png\\64\\default-boy-avatar.png"),
//                "Ali",
//                new TimeData(d), "Shape adof my heart"));
//        FriendCard dd = new FriendCard(new UserInfoCard(
//                new File("src\\gui\\icons\\png\\64\\default-boy-avatar.png"),
//                "Alasdai",
//                new TimeData(d), "Shasdape of my heart"));
//        JFrame frame = new JFrame("Tesdast");
//
//        frame.add(c);
//        frame.add(dd);
//        frame.setLayout(new FlowLayout());
//
//        frame.setSize(300,200);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
}
