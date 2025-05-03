package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(getCalculated(getFromFile(fromFileName)), toFileName);
    }

    private String getFromFile(String fromFileName) {
        File myFile = new File(fromFileName);
        StringBuilder dataFromFile = new StringBuilder();
        try (FileReader fileReader = new FileReader(myFile);
                BufferedReader reader = new BufferedReader(fileReader)) {
            String value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file: " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private String getCalculated(String builder) {
        StringBuilder calculation = new StringBuilder();
        String[] splitData = builder.split(" ");
        int buy = 0;
        int supply = 0;
        for (String line : splitData) {
            String[] parts = line.split(",");
            if ("buy".equals(parts[0])) {
                buy += Integer.parseInt(parts[1]);
            } else {
                supply += Integer.parseInt(parts[1]);
            }
        }
        return calculation
                .append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(supply - buy).toString();
    }

    private void writeToFile(String calculation, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculation);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
