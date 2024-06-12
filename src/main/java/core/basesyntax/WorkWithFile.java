package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        String[] stringArray = data.split(" ");
        report.append("supply,").append(Integer.parseInt(stringArray[0]))
                .append(System.lineSeparator());
        report.append("buy,").append(Integer.parseInt(stringArray[1]))
                .append(System.lineSeparator());
        report.append("result,").append(Integer.parseInt(stringArray[2]))
                .append(System.lineSeparator());
        return report.toString();
    }

    private String readFile(String fileName) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(fileName));
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
            throw new RuntimeException("Something went wrong");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                    System.out.println("There was an error closing the reader");
                }
            }
        }
    }

    private void writeToFile(String fileName, String report) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                    System.out.println("There was an error closing the writer");
                }
            }
        }
    }
}
