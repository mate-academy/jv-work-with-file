package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, getSumOperationType(fromFileName));
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private String[] getSumOperationType(String fromFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] readFromFile = readFromFile(fromFileName);
        for (String s : readFromFile) {
            String[] splitNameSum = s.split(",");
            if (splitNameSum[OPERATION_TYPE_INDEX].equals("supply")) {
                sumSupply += Integer.parseInt(splitNameSum[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(splitNameSum[AMOUNT_INDEX]);
            }
        }
        return new String[]{String.valueOf(sumSupply), String.valueOf(sumBuy)};
    }

    private void writeToFile(String toFileWrite, String[] sumOperationType) {
        int sumSupply = Integer.parseInt(sumOperationType[OPERATION_TYPE_INDEX]);
        int sumBuy = Integer.parseInt(sumOperationType[AMOUNT_INDEX]);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileWrite))) {
            bufferedWriter.write("supply" + SEPARATOR + sumSupply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy" + SEPARATOR + sumBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result" + SEPARATOR + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
