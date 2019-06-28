package gui.mainPanels;

import javax.swing.*;
import gui.mainPanels.cards.ArtistCard;
import java.io.File;
import java.util.ArrayList;


public class ArtistsPanel extends JPanel {
    private ArrayList<ArtistCard> artistCards;

    public ArtistsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        artistCards = new ArrayList<>();
    }

    public void addArtistCard(String artistName, ImageIcon coverFile, int songNumber) {
        ArtistCard newCard = new ArtistCard(artistName, coverFile, songNumber);
        add(newCard);
        artistCards.add(newCard);
    }

    public void setCardState(String artistName, boolean setToPlaying) {
        //todo when a Artist go to play or pause state this method must be called to update little state icon next to the cover
        for (ArtistCard card : artistCards) {
            if (card.getArtistName().equals(artistName)) {
                card.setStateIcon(setToPlaying);
                break;
            }
        }
    }
}
