package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_KEY_WORD = "supply";
    private static final String BUY_KEY_WORD = "buy";
    private static final String DATA_SEPARATOR = ",";
    private static final int DATA_NAME = 0;
    private static final int DATA_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String line = reader.readLine();
            int supply = 0;
            int buy = 0;
            while (line != null) {
                String[] lineData = line.split(DATA_SEPARATOR);
                if (lineData[DATA_NAME].equals(SUPPLY_KEY_WORD)) {
                    supply += Integer.parseInt(lineData[DATA_VALUE]);
                } else if (lineData[DATA_NAME].equals(BUY_KEY_WORD)) {
                    buy += Integer.parseInt(lineData[DATA_VALUE]);
                }
                line = reader.readLine();
            }
            writeReportToFile(supply, buy, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from file: " + fileFrom, e);
        }
    }

    private void writeReportToFile(int supply, int buy, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fileTo = new File(toFileName);
        stringBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            String output = stringBuilder.toString();
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file: " + fileTo, e);
        }
    }
}
