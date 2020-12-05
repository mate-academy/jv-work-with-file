package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, calculateReport(fromFileName));
    }

    private String calculateReport(String fileName) {
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] token = line.split(DELIMITER);
                for (String data : token) {
                    if (data.equals("buy")) {
                        buy += Integer.parseInt(token[1]);
                    } else if (data.equals("supply")) {
                        supply += Integer.parseInt(token[1]);
                    }
                }
            }
            builder.append("supply,").append(supply).append(System.lineSeparator());
            builder.append("buy,").append(buy).append(System.lineSeparator());
            builder.append("result,").append(supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("File with name: " + fileName + " not found");
        }
        return builder.toString();
    }

    private static void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File with name: " + fileName + " not found");
        }
    }
}
