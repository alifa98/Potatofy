package gui.bottomPanels;

import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class SongInfo extends JPanel {
     SongInfo(){
         super();
         //add(new JLabel("song info"));
         setBackground(MaterialColors.WHITE);
         setBorder(new EmptyBorder(new Insets(5,5,5,5)));
         setLayout(new BorderLayout());
         JLabel imagePanel;
         imagePanel = new JLabel();

         imagePanel.setPreferredSize(new Dimension(64,64));
         imagePanel.setIcon(new ImageIcon("C:\\Users\\Erfan\\APProject\\src\\gui\\icons\\png\\64\\default-girl-avatar.png"));
         add(imagePanel,BorderLayout.WEST);


         JPanel textContainer;
         textContainer = new JPanel();

         textContainer.setLayout(new BorderLayout());

         JLabel songTitle;
         songTitle = new JLabel();

         songTitle.setText("The song title");
         textContainer.add(songTitle,BorderLayout.NORTH);

         JLabel artist;
         artist = new JLabel();

         artist.setText("artist");
         textContainer.add(artist,BorderLayout.SOUTH);

         textContainer.setBorder(new EmptyBorder(new Insets(5,5,5,5)));


         add(textContainer,BorderLayout.CENTER);

     }

}
