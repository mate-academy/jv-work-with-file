package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String originText = readFile(fromFileName);
        String result = getResult(originText);
        writeToFile(result, toFileName);
    }

    private String readFile(String originFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(originFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String text = reader.readLine();
            while (text != null) {
                stringBuilder.append(text).append(System.lineSeparator());
                text = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private static String getResult(String text) {
        String[] textData = text.split("\\W+");
        int resultSupply = 0;
        int resultBuy = 0;
        for (int i = 0; i < textData.length; i++) {
            if (textData[i].equals(SUPPLY)) {
                resultSupply += Integer.parseInt(textData[i + 1]);
            } else if (textData[i].equals(BUY)) {
                resultBuy += Integer.parseInt(textData[i + 1]);
            }
        }
        int totalResult = resultSupply - resultBuy;
        return SUPPLY + COMMA + resultSupply + System.lineSeparator()
                + BUY + COMMA + resultBuy + System.lineSeparator()
                + RESULT + COMMA + totalResult;
    }

    private static void writeToFile(String result, String toFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
