package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFromFile(fromFileName).trim();
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                if (split[0].equals("buy")) {
                    buy = buy + Integer.parseInt(split[1]);
                } else {
                    supply = supply + Integer.parseInt(split[1]);
                }
                line = reader.readLine();
                result = supply - buy;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return stringBuilder.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result,").append(supply - buy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file" + toFileName, e);
        }
    }
}
