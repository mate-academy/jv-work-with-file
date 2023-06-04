package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;
    private int buy = 0;
    private int supply = 0;

    private void parsFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String value = reader.readLine();
            while (value != null) {
                String[] lineInFile = value.split(",");
                if (lineInFile[OPERATION_TYPE] != null
                        && lineInFile[OPERATION_TYPE].equals("buy")) {
                    buy += Integer.parseInt(lineInFile[AMOUNT]);
                }
                if (lineInFile[OPERATION_TYPE] != null
                        && lineInFile[OPERATION_TYPE].equals("supply")) {
                    supply += Integer.parseInt(lineInFile[AMOUNT]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void createReport(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            stringBuilder
                    .append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
            writer.write(stringBuilder.toString());
            buy = 0;
            supply = 0;
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        parsFile(fromFileName);
        createReport(toFileName);
    }
}
