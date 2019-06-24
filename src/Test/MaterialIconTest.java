package Test;

import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MaterialIconTest {
   public static JLabel label;
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(800,600);
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAYLIST_ADD, 40, new Color(255, 11, 219));
       label = new JLabel(icon);
       icon=IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAYLIST_ADD, 40, new Color(0, 0, 0));
       label.setIcon(icon);
        f.add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MaterialUIMovement.add(label, MaterialColors.ORANGE_50);
        f.setVisible(true);

    }
}
