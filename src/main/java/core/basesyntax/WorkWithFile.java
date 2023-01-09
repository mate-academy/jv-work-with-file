package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(processingData(readFile(fromFileName)), toFileName);
    }

    private String processingData(String stringFile) {
        final int numberAmount = 1;
        StringBuilder stringBuilder;
        int buySum = 0;
        int supplySum = 0;
        String[] inOrder = stringFile.split(System.lineSeparator());
        String[] word;
        for (String order: inOrder) {
            word = order.split(",");
            if (word[0].equals("buy")) {
                buySum += Integer.parseInt(word[numberAmount]);
            } else {
                supplySum += Integer.parseInt(word[numberAmount]);
            }
        }
        int result = supplySum - buySum;
        stringBuilder = new StringBuilder();
        stringBuilder.append("supply").append(",").append(supplySum).append(System.lineSeparator())
                .append("buy").append(",").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
        return stringBuilder.toString();
    }

    private void writeFile(String resultToFile, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
