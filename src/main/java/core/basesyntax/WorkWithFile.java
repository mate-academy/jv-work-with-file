package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] resultArray = readFile(fromFileName);
        supply = Integer.parseInt(resultArray[0]);
        buy = Integer.parseInt(resultArray[1]);

        int result = supply - buy;
        String report = generateReport(supply, buy, result);

        writeFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                int value = Integer.parseInt(parts[1]);
                if (parts[0].equals("supply")) {
                    supply += value;
                } else {
                    buy += value;
                }
                line = reader.readLine();
            }
            return new String[]{String.valueOf(supply), String.valueOf(buy)};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String generateReport(int supply, int buy, int result) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator());
        reportBuilder.append("buy,")
                .append(buy)
                .append(System.lineSeparator());
        reportBuilder.append("result,")
                .append(result);
        return reportBuilder.toString();
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("");
            writer.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
