package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        createNewFile(toFileName);
        int[] readFromFileResult = readFromFile(fromFileName);
        writeContent(readFromFileResult, toFileName);

    }

    private int[] readFromFile(String fromFileName) {
        try (Scanner scanner = new Scanner(new FileReader(fromFileName))) {
            int buySum = 0;
            int supplySum = 0;

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
            return new int[] {buySum, supplySum};

        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeContent(int[] readFromFileMethod, String toFileName) {
        int buySum = readFromFileMethod[0];
        int supplySum = readFromFileMethod[1];

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

    private void createNewFile(String toFileName) {
        try {
            File resultFile = new File(toFileName);
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }
    }
}
