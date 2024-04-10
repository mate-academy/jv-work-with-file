package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int QUANTITY_INDEX = 1;
    private static final int ACTION_INDEX = 0;
    private static final String SUPPLY_ACTION = "supply";
    private static final String BUY_ACTION = "buy";
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        supply = 0;
        buy = 0;
        readFromFile(fromFileName);
        String report = createReport(supply, buy);
        writeToFile(report, toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = fromFileReader.readLine()) != null) {
                String[] dataFromFile = value.split(",");
                if (dataFromFile[ACTION_INDEX].equals(SUPPLY_ACTION)) {
                    supply = supply + Integer.parseInt(dataFromFile[QUANTITY_INDEX]);
                }
                if (dataFromFile[ACTION_INDEX].equals(BUY_ACTION)) {
                    buy = buy + Integer.parseInt(dataFromFile[QUANTITY_INDEX]);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        return new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(result).toString();
    }

    private void writeToFile(String statisticToFile, String toFileName) {
        try (BufferedWriter toFileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            toFileWriter.write(statisticToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
