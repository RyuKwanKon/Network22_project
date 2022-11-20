import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }

    public void start(){

        Socket socket = null;
        BufferedReader inFromServer = null;


        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("[Enter Nickname]");
            String name = scanner.nextLine();

            socket = new Socket("127.0.0.1", 1111);
            System.out.println("[Connected to the Server]");
            ClientThread clientThread = new ClientThread(socket, name);
            clientThread.start();

            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while(inFromServer != null){
                String chat = inFromServer.readLine();
                if(("[" + name + "] has left the game").equals(chat))
                    break;
                System.out.println("From:" + chat);

                if("모든 유저가 접속했습니다".equals(chat)){
                    a z = new a(socket, name);
                    z.start();
                }

            }

        } catch (IOException e){
            System.out.println("[Lost server connection]");
        } finally {
            try{
                socket.close();

            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("[Server connection terminated]");
        }
    }
}
class ClientThread extends Thread{
    int a = 0;
    Socket chatSocket = null;
    String name;

    Scanner scanner = new Scanner(System.in);
    public ClientThread(Socket chatSocket, String name){
        this.chatSocket = chatSocket;
        this.name = name;
    }

    public void run(){
        try{
            PrintStream outToClient = new PrintStream(chatSocket.getOutputStream());
            outToClient.println(name);
            outToClient.flush();

            while(true){
                String chat = scanner.nextLine();
                outToClient.println("chat : " + chat);
                outToClient.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

class a extends Thread{
    int a = 0;
    Socket socket = null;
    String name;

    //Scanner scanner = new Scanner(System.in);
    public a(Socket socket, String name){
        this.socket = socket;
        this.name = name;
    }

    public synchronized void run(){
        try{
            wait(300);
            PrintStream outToClient = new PrintStream(socket.getOutputStream());
            outToClient.println("data : 게임 서버 접속");
            outToClient.flush();

            JFrame f = new JFrame(name + "'s window");
            f.setSize(400, 200);
            f.setLayout(new FlowLayout());
            TextField tf = new TextField(20);
            JButton b = new JButton("Click");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String str = tf.getText();
                    outToClient.println("data : " + str);
                    outToClient.flush();
                }
            });
            f.add(tf);
            f.add(b);
            f.setVisible((true));

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}


