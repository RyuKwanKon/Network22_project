package Lee;

import Component.*;
import Page.GamePagePanel.GamePage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static Frame.Frame.LodingPage;

public class LodingImg extends JLabel{
    public LodingImg(){
        setBounds(550,250,150,150);
        setLayout(null);
        add(new GamePage());
        try{
            BufferedImage tempImg = ImageIO.read(new File("./assets/LodingTimer.png"));
            ImageIcon temp_img = new ImageIcon(tempImg);
            Image t = temp_img.getImage().getScaledInstance(980, 750, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(t));
            setBorder(null);

        }catch (Exception e){
            System.out.println(e);
        }

    }
  /*  class LodingTime extends JLabel{
        LodingTime(){
            try{
                BufferedImage tempImg = ImageIO.read(new File("./assets/LodingTimer.png"));
                ImageIcon temp_img = new ImageIcon(tempImg);
                Image t = temp_img.getImage().getScaledInstance(980, 750, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(t));
                setBorder(null);
            }catch (Exception e){
                System.out.println(e);
            }
        }*/
  //  }
}
