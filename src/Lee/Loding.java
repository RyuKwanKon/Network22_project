package Lee;
import Page.GamePagePanel.CardView;
import Page.GamePagePanel.GamePage;
import Page.GamePageView;

import Component.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;

import static Event.MainTimer.randNumber;
import static Event.MainTimer.randTitle;


public class Loding extends JPanel {


    public static JLabel Lodingimg=new LodingImg();
    public Loding() {
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        add(new CurrentTitle(610, 210, "대"));
        add(new CurrentTitle(630, 210, "기"));
        add(new CurrentTitle(650, 210, "중"));
        add(new CurrentTitle(670, 210, "."));
        add(new CurrentTitle(690, 210, "."));
        add(new CurrentTitle(710, 210, "."));
        add(new GamePage());
    }

    class LodingPicture extends JLabel {
        LodingPicture() {
            try {
                setBounds(300, 0, 980, 750);
                BufferedImage tempImg = ImageIO.read(new File("./assets/LoginBackground.png"));
                ImageIcon temp_img = new ImageIcon(tempImg);
                Image t = temp_img.getImage().getScaledInstance(980, 750, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(t));
                setBorder(null);


            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    class CurrentTitle extends JLabel{
        CurrentTitle(int x, int y, String title){
            setText(title);
            setBounds(x, y, 600, 400);
            setFont(FontStyle.BidFont);
            setForeground(new Color(154, 254, 132));
        }
    }
}