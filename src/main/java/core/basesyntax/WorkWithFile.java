package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int[] supplyAndBuyArray = readFile(file);
        int supply = supplyAndBuyArray[0];
        int buy = supplyAndBuyArray[1];

        file = new File(toFileName);
        writeToFile(file, supply, buy);
    }

    public void writeToFile(File file, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(SUPPLY_NAME)
                    .append(SEPARATOR)
                    .append(supply)
                    .append(System.lineSeparator());

            stringBuilder.append(BUY_NAME)
                    .append(SEPARATOR)
                    .append(buy)
                    .append(System.lineSeparator());

            stringBuilder.append(RESULT_NAME)
                    .append(SEPARATOR)
                    .append(supply - buy);

            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + file, e);
        }
    }

    public int[] readFile(File file) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                if (currentLine.contains(SUPPLY_NAME)) {
                    currentLine = currentLine.replaceAll("[^\\d-]", "");
                    supply += Integer.parseInt(currentLine);
                }

                if (currentLine.contains(BUY_NAME)) {
                    currentLine = currentLine.replaceAll("[^\\d-]", "");
                    buy += Integer.parseInt(currentLine);
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
        return new int[]{supply, buy};
    }
}
