package lab_4;

import lab_4.exceptions.DuplicateModelNameException;
import lab_4.exceptions.ModelPriceOutOfBoundsException;
import lab_4.transports.Car;
import lab_4.transports.Transport;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Transport car = new Car("car3", 2);
            car.addModel("ca3", 666);
            Transport[] transport = new Transport[]{
                    new Car("Aston Martin", 2),
                    new Car("Audi", 2),
                    car
            };
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 4221));

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(transport);
            Scanner scanner = new Scanner(socket.getInputStream());
            if (scanner.hasNextLine()) {
                System.out.println("\nСреднее арифметическое цен: " + scanner.nextLine());
            }

            outputStream.close();
            scanner.close();
            socket.close();

        } catch (ModelPriceOutOfBoundsException | DuplicateModelNameException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

