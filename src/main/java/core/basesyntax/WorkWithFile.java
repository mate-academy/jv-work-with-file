package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    private int result = 0;
    private String statisticToFile;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        createReport(supply, buy);
        writeToFile(statisticToFile, toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = fromFileReader.readLine()) != null) {
                String[] dataFromFile = value.split(",");
                if (dataFromFile[ACTION_INDEX].equals(SUPPLY_ACTION)) {
                    this.supply = supply + Integer.parseInt(dataFromFile[QUANTITY_INDEX]);
                }
                if (dataFromFile[ACTION_INDEX].equals(BUY_ACTION)) {
                    this.buy = buy + Integer.parseInt(dataFromFile[QUANTITY_INDEX]);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
    }

    private void createReport(int supply, int buy) {
        this.result = supply - buy;
        this.statisticToFile = new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(result).toString();
    }

    private void writeToFile(String statisticToFile, String toFileName) {
        File file = new File(toFileName);
        if (file.length() == 0) {
            try (BufferedWriter toFileWriter = new BufferedWriter(new FileWriter(toFileName))) {
                toFileWriter.write(statisticToFile);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file" + toFileName, e);
            }
        }
    }
}
