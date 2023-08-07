package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] countOptions = readingFile(fromFileName);
        String report = createReport(countOptions);
        writingFile(toFileName, report);
    }

    private int[] readingFile(String fromFileName) {
        int supplyOperation = 0;
        int buyOperation = 0;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                if (strings[0].equals("supply")) {
                    supplyOperation += Integer.parseInt(strings[1]);
                } else {
                    buyOperation += Integer.parseInt(strings[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t open file for reading", e);
        }
        return new int[]{supplyOperation, buyOperation};
    }

    private void writingFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can`t open file for writting", e);
        }
    }

    private String createReport(int[] countOptions) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(countOptions[0])
                .append(System.lineSeparator())
                .append("buy,").append(countOptions[1])
                .append(System.lineSeparator())
                .append("result,").append(countOptions[0] - countOptions[1])
                .append(System.lineSeparator())
                .toString();
    }
}
