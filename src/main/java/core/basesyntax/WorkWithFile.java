package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File read = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(read))) {
            StringBuilder temporary = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                temporary.append(value).append(" ");
                value = reader.readLine();
            }
            String[] lines = temporary.toString().split(" ");
            int supply = 0;
            int buy = 0;
            for (String line : lines) {
                String[] operation = line.split(COMA);
                if (operation[OPERATION_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(operation[AMOUNT_INDEX]);
                } else {
                    buy += Integer.parseInt(operation[AMOUNT_INDEX]);
                }
            }
            createReport(supply, buy, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void createReport(int supply, int buy, String toFileName) {
        StringBuilder stringBuilder1 = new StringBuilder();
        File report = new File(toFileName);
        stringBuilder1
                .append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            writer.write(stringBuilder1.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
