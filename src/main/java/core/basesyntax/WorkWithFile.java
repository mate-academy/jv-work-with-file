package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, createReport(readFile(fromFileName)));
    }

    private List<String> readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                data.add(value);
                data.add(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return data;
    }

    private String createReport(List<String> data) {
        int sumSupply = 0;
        int sumBuy = 0;
        int resultNumbers;
        StringBuilder returnData = new StringBuilder();

        for (String datum : data) {
            String[] splittedLine = datum.split(",");
            if (splittedLine[OPERATION_INDEX].equals("supply")) {
                int numberForSum = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                sumSupply += numberForSum;
            } else if (splittedLine[OPERATION_INDEX].equals("buy")) {
                int numberForSum = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                sumBuy += numberForSum;
            }
        }
        resultNumbers = sumSupply - sumBuy;

        returnData.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(resultNumbers);

        return returnData.toString();
    }

    private void writeFile(String toFileName, String text) {
        File finalFile = new File(toFileName);

        try (BufferedWriter writerToFinalFile = new BufferedWriter(
                new FileWriter(finalFile, true))) {
            writerToFinalFile.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
