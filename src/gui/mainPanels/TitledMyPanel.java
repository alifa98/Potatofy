package gui.mainPanels;

import com.manager.Manager;
import gui.ImageLabel;
import mdlaf.utils.MaterialFonts;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TitledMyPanel extends MyPanel {
    public TitledMyPanel(String title, ArrayList<Song> list, Manager manager) { //passed for mouse event listener
        super(false, manager);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        ImageIcon cover = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src\\gui\\icons\\png\\64\\playlists.png").getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        if (list.size() > 0)
            cover = list.get(0).getAlbumImageAsSize(100, 100);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 5;
        ImageLabel coverLabel = new ImageLabel(cover);
        coverLabel.setBorder(new EmptyBorder(new Insets(10, 5, 10, 10)));
        titlePanel.add(coverLabel, gridBagConstraints);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(MaterialFonts.BLACK.deriveFont(30f));
        titleLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        titlePanel.add(titleLabel, gridBagConstraints);

        JLabel numberOfSongs = new JLabel(list.size() + " Songs");
        numberOfSongs.setFont(MaterialFonts.ITALIC);
        numberOfSongs.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 2;
        titlePanel.add(numberOfSongs, gridBagConstraints);

        titlePanel.setMaximumSize(new Dimension(2500, (int) getPreferredSize().getHeight()));

    }
}
