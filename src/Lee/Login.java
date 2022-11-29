package Lee;

import Page.GamePagePanel.GamePage;

import javax.swing.*;

public class Login extends JPanel {
    public static JTextField loginInput = new InputUser();
    public static JButton loginButton = new InputButton();
    public Login(){
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setOpaque(false);
        setFocusable(true);
        requestFocus();
        add(loginInput);
        add(loginButton);
        add(new GamePage());
    }
    /*class MainPicture extends JLabel{
        MainPicture(){
            try{
                setOpaque(false);
                setBounds(300, 0, 980, 750);
                BufferedImage tempImg = ImageIO.read(new File("./assets/LoginBackground.png"));
                ImageIcon temp_img = new ImageIcon(tempImg);
                Image t = temp_img.getImage().getScaledInstance(980, 750, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(t));
                setBorder(null);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }*/
}
