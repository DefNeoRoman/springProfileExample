package anySchoolProgramming.workingChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ChatClient extends JFrame implements Runnable{
    protected Socket socket;
    protected DataInputStream inputStream;
    protected DataOutputStream outputStream;
    protected JTextArea outTextArea;
    protected JTextField inTextField;
    protected boolean isOn;

    public ChatClient(String title, Socket socket, DataInputStream inputStream, DataOutputStream outputStream){
        super(title);
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.CENTER, outTextArea = new JTextArea());
        outTextArea.setEditable(false);
        cp.add(BorderLayout.SOUTH, inTextField = new JTextField());
        inTextField.addActionListener((e -> {
            try{
                outputStream.writeUTF(inTextField.getText());
                outputStream.flush();
            }catch (Exception c){
                c.printStackTrace();
                isOn=false;
            }
            inTextField.setText("");

        }));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                isOn = false;
                try{
                    outputStream.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                try{
                    socket.close();
                }catch(IOException ex2){
                    ex2.printStackTrace();
                }

            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        setVisible(true);
        inTextField.requestFocus();
        (new Thread(this)).start();
    }
    @Override
    public void run() {
        isOn = true;
        try{
            while(isOn){
                String line = inputStream.readUTF();
                outTextArea.append(line+"\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            inTextField.setVisible(false);
            validate();

        }
    }

    public static void main(String[] args) throws IOException {
        String args0 = "localhost";
        String args1 = "8082";

        Socket socket = new Socket(args0,Integer.parseInt(args1));
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {


            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            new ChatClient("Chat" + args0 + ":" + args1, socket, dis, dos);
        } catch (IOException e){
            e.printStackTrace();
            try{
                if(dos != null) dos.close();
            }catch (IOException ex2){
                ex2.printStackTrace();
            }
            try{
                socket.close();
            }catch(IOException ex3){
                ex3.printStackTrace();
            }
        }
    }
}
