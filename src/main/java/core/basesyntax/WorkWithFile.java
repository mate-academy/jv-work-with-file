package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        writeResultToFile(toFileName, generateReport(readFile(fromFileName)));
    }

    private static String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
            return builder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            String[] splittedLine = line.split(",");
            if (splittedLine[0].equals("supply")) {
                supply += Integer.parseInt(splittedLine[1]);
            }
            if (splittedLine[0].equals("buy")) {
                buy += Integer.parseInt(splittedLine[1]);
            }
        }
        int result = supply - buy;

        String report = new StringBuilder("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result)
                .toString();

        return report;
    }

    private void writeResultToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
