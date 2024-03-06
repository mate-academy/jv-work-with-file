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
        String[] statisticsArray = readFromFile(fromFileName)
                .split(System.lineSeparator());
        int sumBuy = 0;
        int sumSupply = 0;
        for (String statistic : statisticsArray) {
            String[] splittedArray = statistic.split(",");
            if (splittedArray[OPERATION_TYPE_INDEX].equals("buy")) {
                int buy = Integer.parseInt(splittedArray[AMOUNT_INDEX]);
                sumBuy += buy;
            } else if (splittedArray[OPERATION_TYPE_INDEX].equals("supply")) {
                int supply = Integer.parseInt(splittedArray[AMOUNT_INDEX]);
                sumSupply += supply;
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder textToWrite = new StringBuilder();
        textToWrite.append("supply,")
                .append(sumSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(sumBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        writeTofile(toFileName, textToWrite.toString());
    }

    public String readFromFile(String fileToRead) {
        StringBuilder text = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line)
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text.toString();
    }

    public void writeTofile(String fileToWrite, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
