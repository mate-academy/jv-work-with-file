package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] data = readFromFile(fromFileName).split(System.lineSeparator());
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (String value : data) {
            if (value.startsWith(SUPPLY)) {
                sumOfSupply += Integer.parseInt(value.split(",") [1]);
            }
            if (value.startsWith(BUY)) {
                sumOfBuy += Integer.parseInt(value.split(",") [1]);
            }
        }
        int result = sumOfSupply - sumOfBuy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(',').append(sumOfSupply).append(System.lineSeparator())
                .append(BUY).append(',').append(sumOfBuy).append(System.lineSeparator())
                .append(RESULT).append(',').append(result).append(System.lineSeparator());
        writeToFile(report.toString(), toFileName);
    }

    private String readFromFile(String fromFileName) {
        String dataResult = "";
        File file = new File(fromFileName);
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                result.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " is not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file " + fromFileName, e);
        }
        dataResult += result.toString();
        return dataResult;
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
