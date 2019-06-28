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
    private JScrollPane currentMainPanel;

    //main panels
    private SongsPanel songsPanel = new SongsPanel();
    private AlbumsPanel albumsPanel = new AlbumsPanel();
    private ArtistsPanel artistsPanel = new ArtistsPanel();
    private PlayListsPanel playListsPanel = new PlayListsPanel();
    private FavoriteSongsPanel favoriteSongsPanel = new FavoriteSongsPanel();

    //create scrollable panels
    private JScrollPane scrollableSongsPanel = new JScrollPane(songsPanel);
    private JScrollPane scrollableAlbumsPanel = new JScrollPane(albumsPanel);
    private JScrollPane scrollableArtistsPanel = new JScrollPane(artistsPanel);
    private JScrollPane scrollablePlayListsPanel = new JScrollPane(playListsPanel);
    private JScrollPane scrollableFavoriteSongsPanel = new JScrollPane(favoriteSongsPanel);

    public MainFrame(String title) {
        super(title);

        // Initialize the Look and feel to material design
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //initialize scroll of panels
        scrollableSongsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableSongsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableSongsPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        scrollableAlbumsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableAlbumsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableAlbumsPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        scrollableArtistsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableArtistsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableArtistsPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        scrollableFavoriteSongsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableFavoriteSongsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableFavoriteSongsPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        scrollablePlayListsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollablePlayListsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePlayListsPanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());


        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1500; //we can use screen size
        int height = 810; //we can use screen size
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setLocationRelativeTo(null);

        //Register Icon Pack
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        //Setting Default Panels
        topPanel = new TopPanel();
        sidePanel = new SidePanel();
        currentMainPanel = scrollableSongsPanel;
        bottomPanel = new BottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(currentMainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }


    public void setMainPanel(JScrollPane mainPanel) {
        remove(currentMainPanel);
        add(mainPanel, BorderLayout.CENTER);
        currentMainPanel = mainPanel;
        //validate();
        repaint();
        System.out.println("Panel " + currentMainPanel + " setted to main"); //for debug

    }

    public SongsPanel getSongsPanel() {
        return songsPanel;
    }

    public JScrollPane getScrollableAlbumsPanel() {
        return scrollableAlbumsPanel;
    }

    public JScrollPane getScrollableArtistsPanel() {
        return scrollableArtistsPanel;
    }

    public JScrollPane getScrollableFavoriteSongsPanel() {
        return scrollableFavoriteSongsPanel;
    }

    public JScrollPane getScrollablePlayListsPanel() {
        return scrollablePlayListsPanel;
    }

    public JScrollPane getScrollableSongsPanel() {
        return scrollableSongsPanel;
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
