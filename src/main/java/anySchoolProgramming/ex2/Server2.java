package anySchoolProgramming.ex2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            while(true){
                Socket socket = serverSocket.accept();

                new Thread(new SocketDispatcher(socket)).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
