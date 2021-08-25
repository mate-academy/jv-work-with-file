package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int COMPARED_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = countResult(fromFileName);
        writeDataToFile(dataFromFile, toFileName);
    }

    private String getDataFromFile(String fileName) {
        String[] temp = new String[2];
        StringBuilder stringBuilder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                temp = value.split(",");
                if (temp[OPERATION_INDEX].equals(SUPPLY)) {
                    totalSupply += Integer.valueOf(temp[COMPARED_INDEX]);
                } else if (temp[OPERATION_INDEX].equals(BUY)) {
                    totalBuy += Integer.valueOf(temp[COMPARED_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file" + fileName, e);
        }

        String readFile = stringBuilder.append(totalSupply)
                .append(System.lineSeparator())
                .append(totalBuy).toString();
        return readFile;
    }

    public String countResult(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] data = getDataFromFile(fromFileName).split(System.lineSeparator());
        int result = Integer.valueOf(data[OPERATION_INDEX])
                - Integer.valueOf(data[COMPARED_INDEX]);
        stringBuilder.append("supply,")
                .append(Integer.valueOf(data[OPERATION_INDEX]))
                .append(System.lineSeparator())
                .append("buy,")
                .append(Integer.valueOf(data[COMPARED_INDEX]))
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        return stringBuilder.toString();
    }

    private void writeDataToFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to the file" + fileName, e);
        }
    }
}
