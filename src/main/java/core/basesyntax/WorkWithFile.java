package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String dataForWrite = getResult(dataFromFile);
        writeToFile(dataForWrite, toFileName);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private String getResult(String[] dataFromFile) {
        StringBuilder builder = new StringBuilder();
        String[] dataOfRow = new String[2];
        int resultOfSupply = 0;
        int resultOfBuy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            dataOfRow = dataFromFile[i].split(",");
            if (dataOfRow[INDEX_OF_OPERATION].equals(SUPPLY)) {
                resultOfSupply += Integer.parseInt(dataOfRow[INDEX_OF_SUM]);
            } else if (dataOfRow[INDEX_OF_OPERATION].equals(BUY)) {
                resultOfBuy += Integer.parseInt(dataOfRow[INDEX_OF_SUM]);
            }
        }
        int result = resultOfSupply - resultOfBuy;

        return builder.append(SUPPLY).append(",").append(resultOfSupply)
                .append(System.lineSeparator()).append(BUY).append(",")
                .append(resultOfBuy).append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }

    private void writeToFile(String dataForWrite, String fileName) {
        File resultFile = new File(fileName);
        try {
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file ", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            bufferedWriter.write(dataForWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
