package gui.sidePanels;

import com.TimeData;
import com.UserInfoCard;
import gui.ImageLabel;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.util.Date;

public class FriendCard extends JPanel {

    public FriendCard(UserInfoCard userInfo) {

        //create and set grid bag layout to card
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        ImageLabel label = new ImageLabel(new ImageIcon(userInfo.getAvatarSrc().getPath()));
        label.setOpaque(true);
        label.setBackground(new Color(255,0,0));
        gridBagLayout.setConstraints(label, gridBagConstraints);
        this.add(label);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel friendNameLabel = new JLabel(userInfo.getFriendName());
        friendNameLabel.setOpaque(true);
        friendNameLabel.setBackground(new Color(255,255,0));
        gridBagLayout.setConstraints(friendNameLabel, gridBagConstraints);
        this.add(friendNameLabel);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel timeLabel = new JLabel(userInfo.getLastSeen().getRelativeTime());
        timeLabel.setOpaque(true);
        gridBagLayout.setConstraints(timeLabel, gridBagConstraints);
        timeLabel.setBackground(new Color(0,05,45));
        this.add(timeLabel);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        JLabel musicNameLabel = new JLabel(userInfo.getMusicName());
        musicNameLabel.setOpaque(true);
        musicNameLabel.setBackground(new Color(0,255,45));
        gridBagLayout.setConstraints(musicNameLabel, gridBagConstraints);
        this.add(musicNameLabel);
    }

    public static void main(String[] args) {
        long d  = new Date().getTime() - 137777777;
        FriendCard c = new FriendCard(new UserInfoCard(
                new File("D:\\works\\JAVA\\APProject\\src\\gui\\icons\\png\\64\\default-boy-avatar.png"),
                "Ali",
                new TimeData(d), "Shape of my heart"));
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(c);
        frame.setVisible(true);
    }
}
