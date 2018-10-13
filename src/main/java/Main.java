import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        CarTrunks carTrunks = new CarTrunks();
        parser.urlManager(carTrunks.getCarTrunks(), "Багажники");
        CarBoxes carBoxes = new CarBoxes();
        parser.urlManager(carBoxes.getCarBoxes(), "Багажные боксы");
    }
}
