package lab_4.threads;

import lab_4.transports.Transport;
import lab_4.transports.TransportStatic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerRunnable implements Runnable{
    Socket socket;
    public ServerRunnable(Socket socket) {
        this.socket= socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            double d = TransportStatic.calcMidPrices((Transport[]) inputStream.readObject());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(d);

            inputStream.close();
            pw.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
