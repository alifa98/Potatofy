package gui.bottomPanels;

import gui.CustomColors;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class SongInfo extends JPanel {

    private JLabel imagePanel;
    private JPanel textContainer;
    private JLabel songTitle;
    private JLabel artist;
     SongInfo(){
         super();
         //add(new JLabel("song info"));
         setBackground(CustomColors.LIGHTER_GRAY);
         setBorder(new EmptyBorder(new Insets(5,5,5,5)));
         setLayout(new BorderLayout());

         imagePanel = new JLabel();

         imagePanel.setPreferredSize(new Dimension(64,64));
         imagePanel.setIcon(new ImageIcon("src\\gui\\icons\\png\\64\\default-girl-avatar.png"));
         add(imagePanel,BorderLayout.WEST);



         textContainer = new JPanel();

         textContainer.setLayout(new BorderLayout());
         textContainer.setBackground(CustomColors.LIGHTER_GRAY);


         songTitle = new JLabel();

         songTitle.setText("The song title");
         textContainer.add(songTitle,BorderLayout.NORTH);


         artist = new JLabel();

         artist.setText("artist");
         textContainer.add(artist,BorderLayout.SOUTH);

         textContainer.setBorder(new EmptyBorder(new Insets(5,5,5,5)));


         add(textContainer,BorderLayout.CENTER);

     }

     void setSongTitle(String songTitle) {
        this.songTitle.setText(songTitle);
    }

     void setArtist(String artist) {
        this.artist.setText(artist);
    }

    void setAlbumImage(Icon imageIcon){
         imagePanel.setIcon(imageIcon);
    }

}
