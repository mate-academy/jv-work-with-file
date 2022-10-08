package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        File file = new File(fromFileName);
        int buySum = 0;
        int supplySum = 0;

        try (Scanner scanner = new Scanner(new FileReader(fromFileName));) {

            while (scanner.hasNextLine()) {
                String[] lineContent = scanner.nextLine().split(",");

                switch (lineContent[0]) {
                    case "buy":
                        buySum += Integer.parseInt(lineContent[1]);
                        break;

                    case "supply":
                        supplySum += Integer.parseInt(lineContent[1]);
                        break;

                    default:
                        throw new RuntimeException("Incorrect element");

                }

            }

        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }

        try {
            File resultFile = new File(toFileName);
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));) {
            writer.write("supply," + supplySum);
            writer.newLine();
            writer.write("buy," + buySum);
            writer.newLine();
            writer.write("result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Can't write content to new file", e);
        }
    }
}
