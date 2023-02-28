package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        StringBuilder stringBuilder = new StringBuilder();
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
        StringBuilder builder = new StringBuilder();
        String report = builder.append(SUPPLY_AMOUNT).append(COMMA_SEPARATOR)
                .append(String.valueOf(resultSupply)).append(System.lineSeparator())
                .append(BUY_AMOUNT).append(COMMA_SEPARATOR).append(String.valueOf(resultBuy))
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA_SEPARATOR).append(String.valueOf(res)).toString();
        return report;
    }

    private static String readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fileName, e);
        }
    }

    private static void writeToFile(String fileName, String result) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file: " + fileName, e);
        }
    }
}
