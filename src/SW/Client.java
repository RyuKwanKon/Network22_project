//package SW;
//
//import Lee.Login;
//import Frame.Frame;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.net.*;
//import java.util.*;
//
//public class Client {
//
//    public void start(){
//
//        Socket socket = null;
//        BufferedReader inFromServer = null;
//        Scanner scanner = new Scanner(System.in);
//
//        try{
//            //클라이언트 서버 연결
//            //소켓 뚫기
//            socket = new Socket("127.0.0.1", 1111);
//            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println("[Connected to the Server]");
//            //소켓이 연결되면 프레인띄우기
//            JFrame main = new Frame();
//            main.setVisible(true);
//            String chat = inFromServer.readLine();
//
//        } catch (IOException e){
//            System.out.println("[Lost server connection]");
//        } finally {
//            try{
//                socket.close();
//
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//            System.out.println("[Server connection terminated]");
//        }
//    }
//}
//class ClientThread extends Thread{
//    int a = 0;
//    Socket chatSocket = null;
//    String name;
//    public PrintStream outToClient = null;
//    Scanner scanner = new Scanner(System.in);
//    public ClientThread(Socket chatSocket, String name){
//        this.chatSocket = chatSocket;
//        this.name = name;
//    }
//
//    public void run(){
//        try{
//            outToClient = new PrintStream(chatSocket.getOutputStream());
//            outToClient.println(name);
//            outToClient.flush();
//            RequestServer requestServer = null;
//            requestServer = new RequestServer(outToClient);
//            while(true){
//                String chat = scanner.nextLine();
//                outToClient.println("chat : " + chat);
//                outToClient.flush();
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//}
//
//class a extends Thread{
//    int a = 0;
//    Socket socket = null;
//    //Scanner scanner = new Scanner(System.in);
//    public a(Socket socket){
//        this.socket = socket;
//    }
//
//    public synchronized void run(){
//        JFrame main = new Frame();
//        main.setVisible(true);
//
//
//        // }
//        /*
//        try{
//
//            wait(300);
//            PrintStream outToClient = new PrintStream(socket.getOutputStream());
//            outToClient.println("data : 게임 서버 접속");
//            outToClient.flush();
//
//            JFrame f = new JFrame(name + "'s window");
//            f.setSize(400, 200);
//            f.setLayout(new FlowLayout());
//            TextField tf = new TextField(20);
//            JButton b = new JButton("Click");
//            b.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String str = tf.getText();
//                    outToClient.println("data : " + str);
//                    outToClient.flush();
//                }
//            });
//            f.add(tf);
//            f.add(b);
//            f.setVisible((true));
//
//        } catch (IOException | InterruptedException e){
//            e.printStackTrace();
//        }
//
//         */
//    }
//}
//
//
