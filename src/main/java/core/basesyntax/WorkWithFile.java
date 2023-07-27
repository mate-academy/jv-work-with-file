package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            Scanner scanner = new Scanner(new File(fromFileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] nameAndNumber = line.split(",");
                String action = nameAndNumber[0];
                int amount = Integer.parseInt(nameAndNumber[1]);

                switch (action) {
                    case "supply":
                        supply += amount;
                        break;
                    case "buy":
                        buy += amount;
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[][] report = {
                {"supply", String.valueOf(supply)},
                {"buy", String.valueOf(buy)},
                {"result", String.valueOf(supply - buy)}
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
