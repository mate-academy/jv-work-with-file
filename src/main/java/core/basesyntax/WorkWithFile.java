package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readeFromFile(fromFileName);
        String result = createResultReport(dataFromFile);
        writeToFile(toFileName, result);
    }

    private String readeFromFile(String configFile) {
        File file = new File(configFile);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + configFile, e);
        }
        return stringBuilder.toString();
    }

    private String createResultReport(String fileContent) {
        String[] lines = fileContent.split(System.lineSeparator());
        int amountSupply = 0;
        int amountBuy = 0;
        for (String line : lines) {
            String[] splitLine = line.split(SEPARATOR);
            if (splitLine[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                amountSupply += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
            if (splitLine[OPERATION_TYPE_INDEX].equals(BUY)) {
                amountBuy += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        String result = SUPPLY + SEPARATOR + amountSupply + System.lineSeparator();
        result += BUY + SEPARATOR + amountBuy + System.lineSeparator();
        result += RESULT + SEPARATOR + (amountSupply - amountBuy);
        return result;
    }

    private void writeToFile(String fileName, String result) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
