
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * An information panel that displays information to users
 *
 * @Team MAGA
 * @Author Gajun Young - 16440714
 * @Author Royal Thomas - 16326926
 * @Author Richard  Otroshchenko - 16353416
 */
public class InformationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int HEIGHT = 30;
    private static final int WIDTH = 24;

    private JTextArea infoArea = new JTextArea("", HEIGHT, WIDTH);

    private static JPanel remainingCards = new JPanel();

    //Constructor
    public InformationPanel() {
        JScrollPane scroll = new JScrollPane(infoArea);
        setPreferredSize(new Dimension(300, 690));

        //border style
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Information Panel"));

        //Ensures text area doesn't expand to the right, but pushes text downwards
        infoArea.setLineWrap(true);
        infoArea.setEditable(false); //Non editable, so players can't text here
        infoArea.setMaximumSize(infoArea.getPreferredSize());
        add(scroll);

        remainingCards.add(new JLabel("Undealt Cards:"));
        try {
            remainingCards.add(CluedoUI.imageToResizedLabel("empty.png", 45, 70));
            remainingCards.add(CluedoUI.imageToResizedLabel("empty.png", 45, 70));
            remainingCards.add(CluedoUI.imageToResizedLabel("empty.png", 45, 70));
        }catch(IOException ex){

        }
        add(remainingCards);
    }

    /**
     * A method that updates the content on the information panel that is displayed to everyone
     *
     * @param value - The string of text to be displayed
     */
    public void updateContent(String value) {
        // Add string below current value.
        infoArea.append(value + "\n");

        // Auto scroll down to current position.
        infoArea.setCaretPosition(infoArea.getDocument().getLength());
    }

    /**
     * Method to clear information area
     */

    public void clearContent(){
        infoArea.setText("");
    }



    /**
     * Updates the UI to show the cards that are not dealt to the players
     *
     * @param cards An ArrayList of cards that are not dealt to the players
     * @return Nothing
     */
    public static void updateRemainingCards(ArrayList<Card> cards) {
        if (!cards.isEmpty()) {
            remainingCards.removeAll(); // Clean it up
            remainingCards.add(new JLabel("Undealt Cards:"));
            for (Card c : cards) { // Add each card to the panel one by one
                try {
                    // Fetch the image based on the card by removing spaces and
                    // making it lower case
                    String cardName = c.getName().toLowerCase().replaceAll(" ", "")
                            + ".jpg";
                    remainingCards.add(CluedoUI.imageToResizedLabel(cardName, 45, 70)); // Add it to the panel
                } catch (IOException ex) {
                    System.out.println("Unable to locate files for undealt cards.");
                }
            }
            remainingCards.updateUI(); // Refresh the UI to show changes.
        }
    }
}
