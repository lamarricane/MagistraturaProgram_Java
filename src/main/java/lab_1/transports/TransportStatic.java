package lab_1.transports;

public class TransportStatic {

    public static void getAllModelPrices(Transport transport) {
        for (double price : transport.getAllModelPrices()
        ) {
            System.out.println("Prices:");
            System.out.println(price);
        }
    }

    public static void getAllModelNames(Transport transport) {
        for (String name : transport.getAllModelNames()
        ) {
            System.out.println("Model's names:");
            System.out.println(name);
        }
    }

    public static double calcMidPrice(Transport transport) {
        double sum = 0;
        for (double p : transport.getAllModelPrices()) {
            System.out.println(sum);
            sum += p;
        }
        return sum / transport.getAllModelPrices().length;
    }
}