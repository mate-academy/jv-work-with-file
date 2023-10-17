package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String TYPE_SUPPLY = "supply";
    private static final String TYPE_BUY = "buy";
    private static final String TYPE_RESULT = "result";
    private static final String SPECIFIC_CHARACTER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String dataToFile = dataProcess(dataFromFile);
        writeToFile(toFileName, dataToFile);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String dataProcess(String toFileName) {
        String[] dataFromFile = toFileName.toString().split(" ");
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            String[] dataOnLine = dataFromFile[i].split(SPECIFIC_CHARACTER);
            if (dataOnLine[OPERATION_TYPE_INDEX].equals(TYPE_SUPPLY)) {
                amountOfSupply += Integer.parseInt(dataOnLine[SECOND_INDEX]);
            }
            if (dataOnLine[OPERATION_TYPE_INDEX].equals(TYPE_BUY)) {
                amountOfBuy += Integer.parseInt(dataOnLine[SECOND_INDEX]);
            }
        }
        int finalAmount = amountOfSupply - amountOfBuy;
        StringBuilder result = new StringBuilder();
        result = result.append(TYPE_SUPPLY + SPECIFIC_CHARACTER + amountOfSupply
                        + System.lineSeparator()).append(TYPE_BUY + SPECIFIC_CHARACTER
                        + amountOfBuy + System.lineSeparator()).append(TYPE_RESULT
                        + SPECIFIC_CHARACTER + finalAmount + System.lineSeparator());
        return result.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

}

