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
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> resultReadFile = readFile(fromFileName);
        String completedText = createReport(resultReadFile);
        writeFile(toFileName, completedText);
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
            String[] splitLine = datum.split(COMA);
            if (splitLine[OPERATION_INDEX].equals(SUPPLY)) {
                int numberForSum = Integer.parseInt(splitLine[AMOUNT_INDEX]);
                sumSupply += numberForSum;
            } else if (splitLine[OPERATION_INDEX].equals(BUY)) {
                int numberForSum = Integer.parseInt(splitLine[AMOUNT_INDEX]);
                sumBuy += numberForSum;
            }
        }
        resultNumbers = sumSupply - sumBuy;
        returnData.append(SUPPLY + COMA).append(sumSupply).append(System.lineSeparator())
                .append(BUY + COMA).append(sumBuy).append(System.lineSeparator())
                .append(RESULT + COMA).append(resultNumbers);

        return returnData.toString();
    }

    private void writeFile(String toFileName, String text) {
        File finalFile = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(finalFile, true))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
