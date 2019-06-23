package gui.sidePanels;

import com.TimeData;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FriendCard extends JPanel {
    private File avatarSrc;
    private String friendName;
    private TimeData lastSeen;
    private String musicName;

    public FriendCard(File avatarSrc, String friendName, TimeData lastSeen, String musicName) {

        // initialize fields
        this.avatarSrc = avatarSrc;
        this.friendName = friendName;
        this.lastSeen = lastSeen;
        this.musicName = musicName;

        //create and set grid bag layout to card
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.gridwidth = 2;

        //todo create image and set its constrains in next line
        //gridBagLayout.setConstraints( ,gridBagConstraints);
        //this.add()

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel friendNameLabel = new JLabel(this.friendName);
        gridBagLayout.setConstraints(friendNameLabel, gridBagConstraints);
        this.add(friendNameLabel);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel timeLabel = new JLabel(this.lastSeen.getRelativeTime());
        gridBagLayout.setConstraints(timeLabel, gridBagConstraints);
        this.add(timeLabel);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 4;
        JLabel musicNameLabel = new JLabel(this.musicName);
        gridBagLayout.setConstraints(musicNameLabel, gridBagConstraints);
        this.add(musicNameLabel);
    }
}
