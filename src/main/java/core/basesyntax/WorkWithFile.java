package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            int[] data = readFromFile(fromFileName);
            String report = createReport(data[SUPPLY_INDEX], data[BUY_INDEX]);
            writeToFile(toFileName, report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] readFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String tempStr = bufferedReader.readLine();
        int[] values = new int[2];
        while (tempStr != null) {
            String[] tempStrArr = tempStr.split(",");
            int value = Integer.parseInt(tempStrArr[VALUE_INDEX]);
            if (tempStrArr[ACTION_INDEX].equals("supply")) {
                values[SUPPLY_INDEX] += value;
            } else {
                values[BUY_INDEX] += value;
            }
            tempStr = bufferedReader.readLine();
        }
        return values;
    }

    private void writeToFile(String fileName, String report) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        bufferedWriter.write(report);
        bufferedWriter.close();
    }

    private String createReport(int supply, int buy) {
        return new StringBuilder("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy)
                .toString();
    }
}
