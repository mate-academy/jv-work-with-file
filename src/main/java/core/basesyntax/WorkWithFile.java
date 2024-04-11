package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_ACTION = "supply";
    private static final String BUY_ACTION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] fromFileData = readFromFile(fromFileName);
        String report = createReport(fromFileData);
        writeToFile(report, toFileName);
    }

    private String [] readFromFile(String fromFileName) {
        try (BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            StringBuilder values = new StringBuilder();
            while ((value = fromFileReader.readLine()) != null) {
                values.append(value).append(System.lineSeparator());
            }
            return values.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
    }

    private String createReport(String [] fromFileData) {
        int supply = 0;
        int buy = 0;
        for (String stringFromData : fromFileData) {
            String[] dataFromFile = stringFromData.split(",");
            if (dataFromFile[OPERATION_TYPE_INDEX].equals(SUPPLY_ACTION)) {
                supply = supply + Integer.parseInt(dataFromFile[AMOUNT_INDEX]);
            }
            if (dataFromFile[OPERATION_TYPE_INDEX].equals(BUY_ACTION)) {
                buy = buy + Integer.parseInt(dataFromFile[AMOUNT_INDEX]);
            }
        }
        return new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(supply - buy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter toFileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            toFileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
