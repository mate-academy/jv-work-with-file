package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_OF_COUNT = 1;
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        String calculateData = calculateData(inputData);
        writeToFile(toFileName, calculateData);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                fileBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            return fileBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
    }

    private String calculateData(String inputData) {
        int countSupply = 0;
        int countBuy = 0;
        String[] dataFromFile = inputData.split(" ");
        for (String data : dataFromFile) {
            String[] splittedOnComma = data.split(COMMA);
            if (splittedOnComma[0].equals(BUY)) {
                countBuy += Integer.parseInt(splittedOnComma[INDEX_OF_COUNT]);
            } else {
                countSupply += Integer.parseInt(splittedOnComma[INDEX_OF_COUNT]);
            }
        }
        int result = countSupply - countBuy;
        StringBuilder builderOfAnswer = new StringBuilder();
        return builderOfAnswer.append(SUPPLY).append(COMMA).append(countSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(countBuy)
                .append(System.lineSeparator()).append("result,").append(result).toString();
    }

    private void writeToFile(String toFileName, String calculateData) {
        File file = new File(toFileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(calculateData);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file.", e);
        }
    }
}
