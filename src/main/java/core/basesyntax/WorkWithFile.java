package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        getInformation(fromFileName);
        writeInFile(toFileName);
    }

    private void getInformation(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().split(",");
                if (line[0].equals("supply")) {
                    supply += Integer.parseInt(line[1]);
                } else {
                    buy += Integer.parseInt(line[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read this file");
        }
    }

    private void writeInFile(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder result = new StringBuilder();
            result.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
            bufferedWriter.write(result.toString());

        } catch (IOException e) {
            throw new RuntimeException("Can not write information in this file");
        }
    }
}
