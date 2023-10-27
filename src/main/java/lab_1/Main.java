package lab_1;

import lab_1.exceptions.*;
import lab_1.transports.*;

public class Main {

    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException {

        //проверка методов для объектов Car
        Car car = new Car("Aston Martin", 1);
        String[] names = car.getAllModelNames();
        double[] prices = car.getAllModelPrices();
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println("current Name: " + names[i] + " Price: " + prices[i]);
        }

        car.modifyModelName("Name_0", "Car1");
        prices = car.getAllModelPrices();
        names = car.getAllModelNames();
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println("modifyName Name: " + names[i] + " Price: " + prices[i]);
        }

        car.modifyModelPriceByName("Car1", 5678);
        prices = car.getAllModelPrices();
        names = car.getAllModelNames();
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println("modifyPrice Name: " + names[i] + " Price: " + prices[i]);
        }

        car.addModel("Car2", 3456);
        prices = car.getAllModelPrices();
        names = car.getAllModelNames();
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println("addModel Name: " + names[i] + " Price: " + prices[i]);
        }

        car.removeModel("Car1");
        prices = car.getAllModelPrices();
        names = car.getAllModelNames();
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println("removeModel Name: " + names[i] + " Price: " + prices[i]);
        }

        System.out.println("Arithmetic average: " + TransportStatic.calcMidPrice(car));
        TransportStatic.getAllModelNames(car);
        TransportStatic.getAllModelPrices(car);


        //проверка методов для объектов Motorcycle
        Motorcycle motorcycle = new Motorcycle("Yamaha", 1);
        String[] names1 = motorcycle.getAllModelNames();
        double[] prices1 = motorcycle.getAllModelPrices();
        for (int i = 0; i < motorcycle.getAllModelNames().length; i++) {
            System.out.println("current Name: " + names1[i] + " Price: " + prices1[i]);
        }

        motorcycle.modifyModelName("Name_0", "Bike1");
        prices = motorcycle.getAllModelPrices();
        names = motorcycle.getAllModelNames();
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println("modifyModel Name: " + names[i] + " Price: " + prices[i]);
        }

        motorcycle.modifyModelPriceByName("Bike1", 45678);
        prices1 = motorcycle.getAllModelPrices();
        names1 = motorcycle.getAllModelNames();
        for (int i = 0; i < motorcycle.getAllModelNames().length; i++) {
            System.out.println("modifyPrice Name: " + names1[i] + " Price: " + prices1[i]);
        }

        motorcycle.addModel("Bike2", 98765);
        prices1 = motorcycle.getAllModelPrices();
        names1 = motorcycle.getAllModelNames();
        for (int i = 0; i < motorcycle.getAllModelNames().length; i++) {
            System.out.println("addModel Name: " + names1[i] + " Price: " + prices1[i]);
        }

        motorcycle.removeModel("Bike2");
        prices1 = motorcycle.getAllModelPrices();
        names1 = motorcycle.getAllModelNames();
        for (int i = 0; i < motorcycle.getAllModelNames().length; i++) {
            System.out.println("removeModel Name: " + names1[i] + " Price: " + prices1[i]);
        }

        System.out.println("Arithmetic average: " + TransportStatic.calcMidPrice(motorcycle));
        TransportStatic.getAllModelNames(motorcycle);
        TransportStatic.getAllModelPrices(motorcycle);
    }
}