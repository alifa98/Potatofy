package gui.bottomPanels;

import com.manager.Manager;
import gui.CustomColors;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.components.slider.MaterialSliderUI;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VolumeControl extends JPanel {
    private JSlider slider;
    VolumeControl(){
        //add(new JLabel("SongSlider"));
        //setBackground(MaterialColors.GRAY_200);
        setMinimumSize(new Dimension(150,64));
        setLayout(new BorderLayout());

        JPanel container=new JPanel();

        container.setLayout(new BorderLayout());

        JLabel volumeLabel = new JLabel();
        Icon VolumeIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.VOLUME_UP	, 20, CustomColors.DARK_GRAY);
        volumeLabel.setIcon(VolumeIcon);
        volumeLabel.setOpaque(false);
        container.setBackground(CustomColors.LIGHTERER_GRAY);
        container.add(volumeLabel, BorderLayout.WEST);


        slider = new JSlider(JSlider.HORIZONTAL, 0, BottomPanel.MAX_SLIDER_VALUE, 2);
        //UIManager sliderUI=new UIManager();
        //slider.setUI(new CustomSliderUi(slider));
        slider.setForeground(CustomColors.PRIMARY);
        slider.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        slider.setOpaque(false);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setMaximumSize(new Dimension(250,64));
        slider.setPreferredSize(new Dimension(250,64));
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JSlider sourceSlider = (JSlider) e.getSource();
                BasicSliderUI ui = (MaterialSliderUI) sourceSlider.getUI();
                int value = ui.valueForXPosition(e.getX());
                slider.setValue(value);

            }
        });
        container.add(slider,BorderLayout.CENTER);


        container.setMinimumSize(new Dimension(150,64));
        container.setMaximumSize(new Dimension(150,64));
        container.setPreferredSize(new Dimension(150,64));
        add(container,BorderLayout.EAST);

    }

    void setSliderValue(int value) {
        slider.setValue(value);
    }

    void setVolumeSliderEvents(Manager manager){

        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                manager.volumeSliderMouseUpEvent(slider.getValue());

            }
        });


    }
}
