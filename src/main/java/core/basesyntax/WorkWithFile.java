package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFile(fromFileName);
        writeFile(result, toFileName);
    }

    public String readFile(String fileName) {
        StringBuilder report = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            do {
                if (line.split(COMMA)[OPERATION_TYPE].equals(SUPPLY)) {
                    supply += Integer.parseInt(line.split(COMMA)[AMOUNT]);
                } else if (line.split(COMMA)[OPERATION_TYPE].equals(BUY)) {
                    buy += Integer.parseInt(line.split(COMMA)[AMOUNT]);
                }
                line = reader.readLine();
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return buildReport(report.toString(), buy, supply);
    }

    private void writeFile(String toWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(toWrite);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String buildReport(String fileData, int buy, int supply) {
        StringBuilder report = new StringBuilder(fileData);
        return report.append(SUPPLY)
                .append(COMMA)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(buy)
                .append(System.lineSeparator())
                .append("result")
                .append(COMMA)
                .append(supply - buy).toString();
    }
}


