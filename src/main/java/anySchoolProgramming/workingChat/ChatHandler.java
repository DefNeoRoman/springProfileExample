package anySchoolProgramming.workingChat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ChatHandler extends Thread{
    protected Socket socket;
    protected DataInputStream inputStream;
    protected DataOutputStream outputStream;
    protected boolean isOn;

    protected static List<ChatHandler> handlers = Collections.synchronizedList(new ArrayList<ChatHandler>());

    public ChatHandler(Socket s) throws IOException {
        socket = s;
        inputStream = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        outputStream= new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
    }
    @Override
    public void run() {
        isOn = true;
        try {
        handlers.add(this);
        while(isOn){

                String message = inputStream.readUTF();
                broadcast(message);
        }
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                handlers.remove(this);
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public static void broadcast(String message) throws IOException {
        synchronized (handlers){
            Iterator<ChatHandler> itCh = handlers.iterator();
            while (itCh.hasNext()){
                ChatHandler c = itCh.next();
                try {
                    synchronized (c.outputStream){
                        c.outputStream.writeUTF(message);
                    }
                    c.outputStream.flush();
                }catch(IOException ex){
                    ex.printStackTrace();
                    c.isOn = false;
                }

        }
    }
}}
