package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = readFile(fromFileName);
        String resultString = generateReport(stringFromFile);
        writeFile(resultString, toFileName);
    }

    private String generateReport(String data) {
        int buySum = 0;
        int supplySum = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line: lines) {
            String[] dataFromLine = line.split(",");
            if (dataFromLine[OPERATION_TYPE_INDEX].equals("buy")) {
                buySum += Integer.parseInt(dataFromLine[AMOUNT_INDEX]);
            } else {
                supplySum += Integer.parseInt(dataFromLine[AMOUNT_INDEX]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder resultString = new StringBuilder();
        resultString.append("supply").append(",").append(supplySum).append(System.lineSeparator())
                .append("buy").append(",").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);
        return resultString.toString();
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private void writeFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
