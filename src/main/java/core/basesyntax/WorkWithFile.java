package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY_NAME = "supply";
    public static final String BUY_NAME = "buy";
    public static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File fileToRead = new File(fromFileName);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String stringLine = bufferedReader.readLine();
            while (stringLine != null) {
                fileContent.append(stringLine).append(',');
                stringLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return fileContent.toString();
    }

    private String generateReport(String fileContent) {
        String[] variablesWithValues = fileContent.split(",");
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (int i = 0; i < variablesWithValues.length; i += 2) {
            if (variablesWithValues[i].equals(SUPPLY_NAME)) {
                sumOfSupply += Integer.parseInt(variablesWithValues[i + 1]);
            } else {
                sumOfBuy += Integer.parseInt(variablesWithValues[i + 1]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_NAME).append(',').append(sumOfSupply).append(System.lineSeparator())
                .append(BUY_NAME).append(',').append(sumOfBuy).append(System.lineSeparator())
                .append(RESULT_NAME).append(',').append(sumOfSupply - sumOfBuy);
        return report.toString();
    }

    private void writeToFile(String toFileName, String text) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
