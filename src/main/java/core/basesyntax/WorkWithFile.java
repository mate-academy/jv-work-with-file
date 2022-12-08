package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName).split(" ");
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                dataBuilder.append(value).append(' ');
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:" + fromFileName, e);
        }
        return dataBuilder.toString();
    }

    private String createReport(String[] data) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : data) {
            String[] splitterLine = line.split(",");
            if (splitterLine[OPERATION_TYPE_INDEX].equals("supply")) {
                sumSupply += Integer.parseInt(splitterLine[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(splitterLine[AMOUNT_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder.append("supply,").append(sumSupply).append(System.lineSeparator())
        .append("buy,").append(sumBuy).append(System.lineSeparator())
        .append("result,").append(sumSupply - sumBuy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file:" + toFileName, e);
        }
    }
}
