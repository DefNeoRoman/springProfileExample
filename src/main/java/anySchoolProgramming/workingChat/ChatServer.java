package anySchoolProgramming.workingChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public ChatServer(int port) throws IOException {
      ServerSocket service = new ServerSocket(port);
      try{
          while(true){
              Socket s = service.accept();
              System.out.println("Accepted from " + s.getInetAddress());
              ChatHandler handler = new ChatHandler(s);
              handler.start();
          }
      }catch (IOException e){
          e.printStackTrace();
      }finally{
          service.close();
      }
    }

    public static void main(String[] args) {
       String args0 = "8082";
        try {
            new ChatServer(Integer.parseInt(args0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
