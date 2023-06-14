package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SPLITTER = ",";
    private static final String SPACE = " ";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private final int[] types = new int[2];

    public void getStatistic(String fromFileName, String toFileName) {
        String table = readFile(fromFileName);
        String text = writeToString(processData(table));
        writeToFile(text, toFileName);
    }

    public String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(SPACE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return stringBuilder.toString();
    }

    public int[] processData(String table) {
        String[] data = table.split(SPACE);
        int supply = 0;
        int buy = 0;
        for (String separatedData : data) {
            String[] operationInfo = separatedData.split(COMMA_SPLITTER);
            String operationType = operationInfo[0];
            int amount = Integer.parseInt(operationInfo[1]);
            if (operationType.equals(SUPPLY)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        types[0] = supply;
        types[1] = buy;
        return types;
    }

    public String writeToString(int[] operationAmount) {
        int supply = operationAmount[0];
        int buy = operationAmount[1];
        int result = supply - buy;
        return SUPPLY + COMMA_SPLITTER + supply + System.lineSeparator()
                + BUY + COMMA_SPLITTER + buy + System.lineSeparator()
                + RESULT + COMMA_SPLITTER + result + System.lineSeparator();
    }

    public void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file! ", e);
        }
    }
}
