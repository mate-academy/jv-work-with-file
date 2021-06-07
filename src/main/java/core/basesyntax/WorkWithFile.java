package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splitValue = value.split(DELIMITER);
                if (splitValue[OPERATION_INDEX].equals("supply")) {
                    sumSupply += Integer.parseInt(splitValue[AMOUNT_INDEX]);
                } else {
                    sumBuy += Integer.parseInt(splitValue[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file", e);
        }
        createReport(sumSupply, sumBuy, toFileName);
    }

    private void createReport(int supply, int buy, String toFileName) {
        StringBuilder stringBuilder =
                new StringBuilder("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }
}
