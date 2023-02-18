package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeResultDataToFile(createResultData(readDataFromFile(fromFileName)), toFileName);
    }

    private int[] readDataFromFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] data = value.split(DELIMITER);
                if (data[OPERATION_INDEX].equals("supply")) {
                    supply += Integer.parseInt(data[AMOUNT_INDEX]);
                } else if (data[OPERATION_INDEX].equals("buy")) {
                    buy += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
                result = supply - buy;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supply, buy, result};
    }

    private String createResultData(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(data[SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(data[BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(data[RESULT_INDEX]);
        return stringBuilder.toString();
    }

    private void writeResultDataToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write a file", e);
        }
    }
}
