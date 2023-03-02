package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_AMOUNT = "buy";
    private static final String SUPPLY_AMOUNT = "supply";
    private static final String COMMA_SEPARATOR = ",";
    private static final String RESULT = "result";

    public static void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = getReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private static String getReport(String fromFileName) {
        String[] words = fromFileName.split(COMMA_SEPARATOR);
        int resultSupply = 0;
        int resultBuy = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(SUPPLY_AMOUNT)) {
                resultSupply += Integer.parseInt(words[i + 1]);
            }
            if (words[i].equals(BUY_AMOUNT)) {
                resultBuy += Integer.parseInt(words[i + 1]);
            }
        }
        int res = resultSupply - resultBuy;
        return SUPPLY_AMOUNT + COMMA_SEPARATOR + resultSupply + System.lineSeparator()
                + BUY_AMOUNT + COMMA_SEPARATOR + resultBuy + System.lineSeparator()
                + RESULT + COMMA_SEPARATOR + res;
    }

    private static String readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(COMMA_SEPARATOR);
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fileName, e);
        }
    }

    private static void writeToFile(String fileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file: " + fileName, e);
        }
    }
}
