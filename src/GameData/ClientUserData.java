package GameData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import static Component.Data.cardInfo;
import static Page.GamePageView.CenterCard;
import static Page.GamePageView.alarm;

public class ClientUserData {
    public static String userName;
    public static int coin;
    public static int userBid;
    public static String currentCard = "wait";
    public static LinkedList<String> userList = new LinkedList<String>();

    public static void changeCard(String typeCard){
        try {
            BufferedImage tempImg = null;
            tempImg = ImageIO.read(new File("./assets/Card/" + typeCard + ".png"));
            ImageIcon temp_img = new ImageIcon(tempImg);
            Image t = temp_img.getImage().getScaledInstance(141, 200, Image.SCALE_SMOOTH);
            CenterCard.setIcon(new ImageIcon(t));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
