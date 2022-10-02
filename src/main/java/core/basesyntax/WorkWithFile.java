package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String result = builder.toString();
            return result.split(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Can`t read a file");
        }
        return new String[0];
    }

    public String createReport(String[] data) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder builder = new StringBuilder("supply,");
        for (String record: data) {
            String[] dataByColumns = record.split(DATA_SEPARATOR);
            if (dataByColumns[OPERATION_INDEX].equalsIgnoreCase(SUPPLY)) {
                supplyCount += Integer.parseInt(dataByColumns[AMMOUNT_INDEX]);
            } else if (dataByColumns[OPERATION_INDEX].equalsIgnoreCase(BUY)) {
                buyCount += Integer.parseInt(dataByColumns[AMMOUNT_INDEX]);
            }
        }
        builder.append(supplyCount).append("\nbuy,").append(buyCount)
                .append("\nresult,").append(supplyCount - buyCount);
        return builder.toString();
    }

    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Can`t write to a file");
        }
    }
}
