package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        String[] stringArray = data.split(" ");
        String supply = stringArray[0];
        String buy = stringArray[1];
        String result = stringArray[2];
        report.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result)
                .append(System.lineSeparator());
        return report.toString();
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int supply = 0;
            int buy = 0;
            int result;
            String line = reader.readLine();
            while (line != null) {
                String[] separatedLine = line.split(",");
                if (separatedLine[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(separatedLine[1]);
                } else if (separatedLine[0].equals(BUY)) {
                    buy += Integer.parseInt(separatedLine[1]);
                }
                line = reader.readLine();
            }
            result = supply - buy;
            stringBuilder.append(supply).append(" ").append(buy).append(" ")
                    .append(result);
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("There was an error reading data to the file");
        }
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("There was an error writing data to the file");
        }
    }
}
