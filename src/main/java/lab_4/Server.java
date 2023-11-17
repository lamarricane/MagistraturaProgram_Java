package lab_4;

import lab_4.threads.ServerRunnable;
import lab_4.transports.Transport;
import lab_4.transports.TransportStatic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static void sequentialServer(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            double d = TransportStatic.calcMidPrices((Transport[]) inputStream.readObject());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(d);

            inputStream.close();
            printWriter.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void parallelServer(ServerSocket serverSocket) throws IOException {
        Socket soket = serverSocket.accept();
        Thread thread = new Thread(new ServerRunnable(soket));
        thread.start();
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4221);
            while (true) {
                sequentialServer(serverSocket);
                //parallelServer(serverSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
