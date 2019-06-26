package gui.bottomPanels;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BottomPanel extends JPanel {

    public BottomPanel(){
        super();
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException var24) {
            var24.printStackTrace();
        }
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
        add(new SongInfo(),c);

        c.weightx=1;
        c.gridx=2;
        add(new ControlButtons(),c);

        c.gridx=3;
        //JPanel testPanel=new JPanel();
        c.weightx=5;
        add(new SongSlider(),c);
        //testPanel.setOpaque(false);

//
//        JButton buttonWarning = new JButton();
//        buttonWarning.setOpaque(false);
//        buttonWarning.setBackground(MaterialColors.YELLOW_800);
//        buttonWarning.addMouseListener(MaterialUIMovement.getMovement(buttonWarning, MaterialColors.YELLOW_600));
//        class WarningMessage extends AbstractAction {
//
//            public WarningMessage() {
//                putValue(Action.NAME, "Info warning panel");
//            }
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                JOptionPane optionPane = new JOptionPane();
//                //optionPane.showMessageDialog(this, "This is message warning", "Message warning", JOptionPane.WARNING_MESSAGE);
//            }
//
//        }
//
//        buttonWarning.setAction(new WarningMessage());
        c.gridx=4;
        c.gridy=0;
        c.weightx=1;
        add(new VolumeControl(),c);


    }

}
