package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private int supplyOperation = 0;
    private int buyOperation = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readingFile(fromFileName);
        String report = createReport();
        writingFile(toFileName, report);
    }

    private void readingFile(String fromFileName) {
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
    }

    private void writingFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can`t open file for writting", e);
        }
    }

    private String createReport() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(supplyOperation)
                .append(System.lineSeparator())
                .append("buy,").append(buyOperation)
                .append(System.lineSeparator())
                .append("result,").append(supplyOperation - buyOperation)
                .append(System.lineSeparator())
                .toString();
    }
}
