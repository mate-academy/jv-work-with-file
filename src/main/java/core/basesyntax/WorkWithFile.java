package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORD_SUPPLY = "supply";
    private static final String WORD_BUY = "buy";
    private static final String WORD_RESULT = "result";
    private static final int INDEX_NAME = 0;
    private static final int INDEX_NUMBER = 1;
    private static final String COM_SPRT = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report,toFileName);
    }

    private String readFromFile(String inputFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + inputFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] strings = dataFromFile.split(" ");
        for (int i = 0; i < strings.length; i++) {
            String[] s = strings[i].split(COM_SPRT);
            if (s[INDEX_NAME].equals(WORD_SUPPLY)) {
                sumSupply += Integer.parseInt(s[INDEX_NUMBER]);
            } else {
                sumBuy += Integer.parseInt(s[INDEX_NUMBER]);
            }
        }
        int difference = sumSupply - sumBuy;
        StringBuilder builder = new StringBuilder();
        String writeData = builder.append(WORD_SUPPLY).append(COM_SPRT).append(sumSupply)
                .append(System.lineSeparator())
                .append(WORD_BUY).append(COM_SPRT).append(sumBuy)
                .append(System.lineSeparator())
                .append(WORD_RESULT).append(COM_SPRT).append(difference).toString();
        return writeData;
    }

    private void writeToFile(String outputData, String outputFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            bufferedWriter.write(outputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + outputFileName, e);
        }
    }
}
