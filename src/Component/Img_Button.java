package Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Img_Button extends JButton{
    public Img_Button(String name, int x, int y, int width, int height, String title, Color background, ActionListener a) {
        try{
            if(x == -1 && y == -1){
                setPreferredSize(new Dimension(width, height));
                System.out.println("hi");
            }
            else{
                setLayout(null);
                setBounds(x, y, width, height);
            }
            setBackground(background);
            BufferedImage tempImg = ImageIO.read(new File("./assets/"+title+"Button.png"));
            ImageIcon img = new ImageIcon(tempImg);
            setIcon(img);
            setBorder(null);
            setName(name);
            setVisible(true);
            addActionListener(a);
        }catch (Exception e){

        }
    }
}
