package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = readFile(fromFileName);
        String resultString = generateReport(stringFromFile);
        writeFile(resultString, toFileName);
    }

    private String generateReport(String data) {
        final int numberAmount = 1;
        int buySum = 0;
        int supplySum = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line: lines) {
            String[] dataFromLine = line.split(",");
            if (dataFromLine[0].equals("buy")) {
                buySum += Integer.parseInt(dataFromLine[numberAmount]);
            } else {
                supplySum += Integer.parseInt(dataFromLine[numberAmount]);
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
