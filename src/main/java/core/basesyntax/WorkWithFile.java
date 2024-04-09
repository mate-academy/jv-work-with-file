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
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        createReport(supply, buy);
        writeToFile(builder, toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] dataFromFile = value.split(",");
                if (dataFromFile[ACTION_INDEX].equals(SUPPLY_ACTION)) {
                    this.supply = supply + Integer.parseInt(dataFromFile[QUANTITY_INDEX]);
                }
                if (dataFromFile[ACTION_INDEX].equals(BUY_ACTION)) {
                    this.buy = buy + Integer.parseInt(dataFromFile[QUANTITY_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void createReport(int supply, int buy) {
        this.result = supply - buy;
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
    }

    private void writeToFile(StringBuilder builder, String toFileName) {
        File file = new File(toFileName);
        if (file.length() == 0) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(builder.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
