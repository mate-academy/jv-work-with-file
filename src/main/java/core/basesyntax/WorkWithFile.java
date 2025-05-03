package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String delimiter = ",";
    private static final String supplyText = "supply";
    private static final String buyText = "buy";
    private static final String resultText = "result";
    private static final int operationTypeIndex = 0;
    private static final int amountIndex = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        final String dataFromFile = readFile(fromFileName);
        final String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFile) {
        final StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFile);
        try (BufferedReader info = new BufferedReader(new FileReader(file))) {
            int digitInfo = info.read();
            while (digitInfo != -1) {
                stringBuilder.append((char) digitInfo);
                digitInfo = info.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from file" + fromFile, e);
        }
        return stringBuilder.toString();
    }

    public void writeToFile(String toFile, String text) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFile, e);
        }
    }

    public String createReport(String dataFormFile) {
        final String empty = System.lineSeparator();
        String[] rows = dataFormFile.split(empty);
        int supply = 0;
        int buy = 0;
        for (String row : rows) {
            final String[] values = row.split(delimiter);
            if (values[operationTypeIndex].equals(supplyText)) {
                supply += Integer.parseInt(values[amountIndex]);
            } else {
                buy += Integer.parseInt(values[amountIndex]);
            }
        }
        final int result = supply - buy;
        return supplyText + delimiter + supply + empty
                + buyText + delimiter + buy + empty
                + resultText + delimiter + result;
    }
}
