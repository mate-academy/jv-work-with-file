package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File fileToWrite = new File(toFileName);
        String[] dataFromFile = readFromFile(file);
        String report = createReport(dataFromFile);
        writeToFile(report,fileToWrite);
    }

    private String[] readFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split("\\W");
    }

    private String createReport(String[] dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            if (dataFromFile[i].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(dataFromFile[i + 1]);
            } else if (dataFromFile[i].equals(BUY)) {
                sumBuy += Integer.parseInt(dataFromFile[i + 1]);
            }
        }
        int sumResult = sumSupply - sumBuy;
        StringBuilder writebuilder = new StringBuilder();
        return writebuilder.append(SUPPLY).append(COMMA)
                .append(sumSupply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(sumBuy).append(System.lineSeparator())
                .append("result").append(COMMA).append(sumResult).toString();
    }

    private void writeToFile(String report, File fileToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write file", ex);
        }
    }
}
