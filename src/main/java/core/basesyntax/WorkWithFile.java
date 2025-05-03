package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    int supply = 0;
    int buy = 0;

    public String Supply = "supply";
    public String Buy = "buy";
    public String Result = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (Supply.equalsIgnoreCase(operation)) {
                    supply += amount;
                } else if (Buy.equalsIgnoreCase(operation)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        writeToFile(supply, buy, toFileName);
    }

    public void writeToFile(int supply, int buy, String toFileName) {
        int result = supply - buy;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(Supply + "," + supply + "\n");
            writer.write(Buy + "," + buy + "\n");
            writer.write(Result + "," + result + "\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
