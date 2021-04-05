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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            String[] readLine;
            while (value != null) {
                readLine = value.split(",");
                if (readLine[0].equals("supply")) {
                    supply += Integer.parseInt(readLine[1]);
                } else {
                    buy += Integer.parseInt(readLine[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File does not exist", e);
        }
        writeToFile(toFileName, createReport(supply, buy));
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String file, String finalReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(finalReport);
        } catch (IOException e) {
            throw new RuntimeException("File does not exist", e);
        }
    }
}
