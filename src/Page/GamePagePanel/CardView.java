package Page.GamePagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardView extends JLabel {
    public CardView(int x, int y, int width, int height, String title){
        try{
            if(x == -1 && y == -1){
                setPreferredSize(new Dimension(width, height));
            }
            else{
                setLayout(null);
                setBounds(x, y, width, height);
            }
            setOpaque(false);
            BufferedImage tempImg = ImageIO.read(new File("./assets/Card/"+title+".png"));
            ImageIcon temp_img = new ImageIcon(tempImg);
            Image t = temp_img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(t));
            setBorder(null);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
