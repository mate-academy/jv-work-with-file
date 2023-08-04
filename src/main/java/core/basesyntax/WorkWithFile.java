package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyBuy = new int[2];
        readFile(fromFileName,supplyBuy);
        int report = createReport(supplyBuy[0], supplyBuy[1]);
        writeReportToFile(toFileName, supplyBuy[0], supplyBuy[1], report);
    }

    private void readFile(String fromFileName, int[] supplyBuy) {
        try {
            Scanner scanner = new Scanner(new File(fromFileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] nameAndNumber = line.split(",");
                String action = nameAndNumber[0];
                int amount = Integer.parseInt(nameAndNumber[1]);

                switch (action) {
                    case "supply":
                        supplyBuy[0] += amount; // Update supply value at index 0
                        break;
                    case "buy":
                        supplyBuy[1] += amount; // Update buy value at index 1
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
    }

    private int createReport(int supply, int buy) {
        return supply - buy;
    }

    private void writeReportToFile(String toFileName, int supply, int buy, int result) {
        String[][] report = {
                {"supply", String.valueOf(supply)},
                {"buy", String.valueOf(buy)},
                {"result", String.valueOf(result)}
        };

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            for (String[] row : report) {
                writer.write(row[0] + "," + row[1]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
