package Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Img_Panel extends JLabel{
    public Img_Panel(int x, int y, int width, int height, String title){
        try{
            if(x == -1 && y == -1){
                setPreferredSize(new Dimension(width, height));
            }
            else{
                setLayout(null);
                setBounds(x, y, width, height);
            }
            setOpaque(false);
            BufferedImage tempImg = ImageIO.read(new File("./assets/"+title+".png"));
            ImageIcon temp_img = new ImageIcon(tempImg);
            setIcon(temp_img);
            setBorder(null);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
