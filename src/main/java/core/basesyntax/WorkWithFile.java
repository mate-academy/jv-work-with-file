package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int OPERATION_TYPE_RESULT = 2;
    private static final int OPERATION_TYPE_BUY = 1;
    private static final int OPERATION_TYPE_SUPPLY = 0;
    private static final int AMOUNT = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String resultFromRead = readFromFile(fromFileName);
        String[] resultFromProcessing = infoProcessing(resultFromRead);
        String resultConstruction = infoConstruction(resultFromProcessing);
        writeToFile(toFileName, resultConstruction);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("File can not be open", e);
        }

        return stringBuilder.toString();
    }

    private String[] infoProcessing(String data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        String[] splitData = data.split(System.lineSeparator());

        for (String string : splitData) {
            String[] wordToProcess = string.split(COMMA);
            int amount = Integer.parseInt(wordToProcess[AMOUNT]);
            if (wordToProcess[OPERATION_TYPE].equals(OPERATION_SUPPLY)) {
                supplyCounter += amount;
            } else {
                buyCounter += amount;
            }
        }

        return new String[] {String.valueOf(supplyCounter), String.valueOf(buyCounter),
                String.valueOf(supplyCounter - buyCounter)};
    }

    private void writeToFile(String toFileName, String data) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("can not write to file", e);
        }
    }

    private String infoConstruction(String[] result) {
        Stack<String> stack = new Stack<>();
        stack.push(OPERATION_RESULT + COMMA + result[2]);
        stack.push(OPERATION_BUY + COMMA + result[1]);
        stack.push(OPERATION_SUPPLY + COMMA + result[0]);

        StringBuilder resultBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            resultBuilder.append(stack.pop()).append(System.lineSeparator());
        }

        return resultBuilder.toString();
    }
}
