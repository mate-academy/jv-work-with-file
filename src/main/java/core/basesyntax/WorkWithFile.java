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
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int SUPPLY_DATA = 0;
    private static final int BUY_DATA = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private int[] readFromFile(String fromFileName) {
        int[] result = new int[]{SUPPLY_DATA, SUPPLY_DATA};
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] option = value.split(SEPARATOR);
                if (option[SUPPLY_DATA].equals(SUPPLY)) {
                    result[SUPPLY_DATA] += Integer.valueOf(option[BUY_DATA]);
                } else if (option[SUPPLY_DATA].equals(BUY)) {
                    result[BUY_DATA] += Integer.valueOf(option[BUY_DATA]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return result;
    }

    private String generateReport(int[] data) {
        StringBuilder bilder = new StringBuilder();
        bilder.append(SUPPLY).append(SEPARATOR).append(data[SUPPLY_DATA]).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(data[BUY_DATA]).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(data[SUPPLY_DATA] - data[BUY_DATA]);
        return bilder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        File writeFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, false))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
