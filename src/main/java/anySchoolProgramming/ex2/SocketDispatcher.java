package anySchoolProgramming.ex2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketDispatcher implements Runnable {
    private Socket socket;

    public SocketDispatcher(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            test(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void test(Socket socket) throws IOException {
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
    }
}
