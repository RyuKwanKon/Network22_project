package Page.GamePagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import Component.*;

public class GamePage extends JPanel {
    public GamePage(){
            Color background = new Color(255, 255, 255);
            setLayout(null);
            setBorder(null);
            setBounds(0, 0, 1280, 720);
            setBackground(background);
            setOpaque(false);
    }
    public void paint(Graphics g){
        try{
            BufferedImage tempImg = ImageIO.read(new File("./assets/GameBackGround.png"));
            Image backgoundImg = new ImageIcon(tempImg).getImage();
            g.drawImage(backgoundImg, 0, 0, null);
            setOpaque(false);
        }catch(Exception e){

        }

    }
}
