package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = ",";
    private static final String BUY_TYPE = "buy";
    private static final String SUPPLY_TYPE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {

        createNewFile(toFileName);
        int[] readFromFileResult = readFromFile(fromFileName);
        write(createReport(readFromFileResult), toFileName);

    }

    private int[] readFromFile(String fromFileName) {
        try (Scanner scanner = new Scanner(new FileReader(fromFileName))) {
            int buySum = 0;
            int supplySum = 0;

            while (scanner.hasNextLine()) {

                String[] lineContent = scanner.nextLine().split(LINE_SEPARATOR);

                switch (lineContent[0]) {
                    case BUY_TYPE:
                        buySum += Integer.parseInt(lineContent[1]);
                        break;

                    case SUPPLY_TYPE:
                        supplySum += Integer.parseInt(lineContent[1]);
                        break;

                    default:
                        break;

                }

            }
            return new int[] {supplySum, buySum};

        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void write(String report, String toFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));) {
            writer.write(report);
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

    private String createReport(int[] fileData) {
        int supplyValue = fileData[0];
        int buyValue = fileData[1];

        String report = "supply," + supplyValue + System.lineSeparator()
                + "buy," + buyValue + System.lineSeparator()
                + "result," + (supplyValue - buyValue);
        return report;
    }
}
