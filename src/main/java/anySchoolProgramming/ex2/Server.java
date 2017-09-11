package anySchoolProgramming.ex2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    public static void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            String html = "<html>"+
                    "<head>hh</head>" +
                    "<body>"+
                    "<h1>super server tipa apache</h1>"+

                    "</body>"+
                            "</html>";
            String header = "HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html\n"+
                    "Content-Length:"+html.length()+"\n"+
                    "Connection:close\n\n";

            String resultText = header+html;
            outputStream.write(resultText.getBytes());
            outputStream.flush();//нужно было сделать flush
            outputStream.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
