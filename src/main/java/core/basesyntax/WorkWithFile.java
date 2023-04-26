package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int AMMOUNT_POSITION = 1;
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final String SUPPLY = "supply";

    private int[] readData(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(SEPARATOR);
                if (parts[OPERATION_TYPE_POSITION].equals(SUPPLY)) {
                    supply += Integer.parseInt(parts[AMMOUNT_POSITION]);
                } else {
                    buy += Integer.parseInt(parts[AMMOUNT_POSITION]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return new int[] {supply, buy};
    }

    private String generateReport(int[] data) {
        int result = data[0] - data[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(data[0]).append(System.lineSeparator());
        stringBuilder.append("buy,").append(data[1]).append(System.lineSeparator());
        stringBuilder.append("result,").append(result).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readData(fromFileName);
        String report = generateReport(data);
        writeReport(toFileName, report);
    }
}
