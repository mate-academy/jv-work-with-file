package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMMOUNT_INDEX = 1;
    private int sumSupply = 0;
    private int sumBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                String[] data = value.split(",");
                if (data[OPERATION_TYPE_INDEX].equals("buy")) {
                    sumBuy += Integer.parseInt(data[AMMOUNT_INDEX]);
                } else if (data[OPERATION_TYPE_INDEX].equals("supply")) {
                    sumSupply += Integer.parseInt(data[AMMOUNT_INDEX]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void writeToFile(String toFileName) {
        StringBuilder builder = new StringBuilder();
        int result = sumSupply - sumBuy;
        builder.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result);
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
