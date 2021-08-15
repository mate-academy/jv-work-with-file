package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private final String supply = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(fromFileName), toFileName);
    }

    private String createReport(String fromFileName) {
        BufferedReader bufferedReader = null;
        String row = "";
        int supplyValue = 0;
        int buyValue = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((row = bufferedReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (rowData[0].equals(supply)) {
                    supplyValue += Integer.parseInt(rowData[1]);
                } else {
                    buyValue += Integer.parseInt(rowData[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find the file.", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file.", e);
        }
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(supplyValue).append(System.lineSeparator())
                .append("buy,").append(buyValue).append(System.lineSeparator())
                .append("result,").append(supplyValue - buyValue);
        return result.toString();
    }

    private void writeToFile(String data, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file.", e);
        }
    }
}
