package gui;

import gui.bottomPanels.BottomPanel;
import gui.mainPanels.*;
import gui.sidePanels.SidePanel;
import gui.topPanels.TopPanel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private SidePanel sidePanel;
    private JPanel currrentMainPanel;

    //main panels
    private SongsPanel songsPanel = new SongsPanel();
    private AlbumsPanel albumsPanel = new AlbumsPanel();
    private ArtistsPanel artistsPanel = new ArtistsPanel();
    private PlayListsPanel playListsPanel = new PlayListsPanel();
    private FavoriteSongsPanel favoriteSongsPanel = new FavoriteSongsPanel();

    public MainFrame(String title){
        super(title);

        // Initialize the Look and feel to material design
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =  1500; //we can use screen size
        int height =  810; //we can use screen size
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255,255,255));
        setLocationRelativeTo(null);

        //Register Icon Pack
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        
        //Setting Default Panels
        topPanel = new TopPanel();
        sidePanel = new SidePanel();
        currrentMainPanel = songsPanel;
        bottomPanel = new BottomPanel();

        add(topPanel,BorderLayout.NORTH);
        add(sidePanel,BorderLayout.WEST);
        add(currrentMainPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        remove(currrentMainPanel);
        add(mainPanel);
        currrentMainPanel = mainPanel;
        validate();
    }

    public SongsPanel getSongsPanel() {
        return songsPanel;
    }

    public ArtistsPanel getArtistsPanel() {
        return artistsPanel;
    }

    public AlbumsPanel getAlbumsPanel() {
        return albumsPanel;
    }

    public PlayListsPanel getPlayListsPanel() {
        return playListsPanel;
    }
    public FavoriteSongsPanel getFavoriteSongsPanel() {
        return favoriteSongsPanel;
    }
    public TopPanel getTopPanel() {
        return topPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }


}
