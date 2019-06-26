package gui.bottomPanels;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import media.music.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BottomPanel extends JPanel {

    private SongInfo songInfo;
    private ControlButtons controlButtons;
    private SongSlider songSlider;
    private VolumeControl volumeControl;
    private Song displaySong;

    public BottomPanel(){
        super();
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException var24) {
            var24.printStackTrace();
        }

        songInfo=new SongInfo();
        controlButtons=new ControlButtons();
        songSlider=new SongSlider();
        volumeControl=new VolumeControl();

        setLayout(new GridBagLayout());
        setSize(0,500);
        setMaximumSize(new Dimension(0,500));
        setBackground(MaterialColors.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=0;
        c.gridheight=500;
        c.weightx=1;
        add(songInfo,c);

        c.weightx=1;
        c.gridx=2;
        add(controlButtons,c);

        c.gridx=3;
        c.weightx=5;
        add(songSlider,c);

        c.gridx=4;
        c.weightx=1;
        add(volumeControl,c);


    }

    public void setSong(Song song){
        //should update Album Art, title , artist, Slider label ,song slider
        displaySong=song;
        updatePanels();
    }

    private void updatePanels(){
        //update everything
    }



}
