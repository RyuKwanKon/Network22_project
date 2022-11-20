import javax.swing.*;
import java.awt.Color;
public class login{
    
    JFrame f = new JFrame("Pocker Game");
    JPanel panel=new JPanel();
    JPanel idpanel=new JPanel();
    JPanel a=new JPanel();
    JLabel imgLabel=new JLabel();

    ImageIcon image=new ImageIcon(login.class.getResource("/images/login.png"));

    JButton btn=new JButton("참가하기");

    JLabel label=new JLabel("닉네임");
    
    JTextField textfield=new JTextField(20);
   
    

    public void Test(){
        f.setSize(1280,750);
        
        idpanel.add(label);
        idpanel.add(textfield);
        idpanel.add(btn);
        idpanel.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        
        imgLabel.setIcon(image);
        panel.setLayout(null);

        panel.add(idpanel);
        panel.add(imgLabel);
        panel.setBackground(Color.BLACK);

        idpanel.setBounds(18,350,300,400);
        imgLabel.setBounds(360,-14,980,755);
        f.add(panel);
        f.setVisible(true);
    }
}
