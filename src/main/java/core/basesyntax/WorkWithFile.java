package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value);
                value = reader.readLine();

                if (value != null) {
                    stringBuilder.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        String[] data = stringBuilder.toString().split(System.lineSeparator());

        int supply = 0;
        int buy = 0;
        int result;

        for (String row : data) {
            String[] rowData = row.split(",");

            String operation = rowData[OPERATION_INDEX];
            int number = Integer.parseInt(rowData[NUMBER_INDEX]);

            if (operation.equals("supply")) {
                supply += number;
                continue;
            }

            buy += number;
        }

        result = supply - buy;

        stringBuilder.setLength(0);
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into file", e);
        }
    }
}
