package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int NAME_POSITION = 0;
    static final int NUM_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        int sumSupply = 0;
        int sumBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        String[] array = builder.toString().split(System.lineSeparator());

        for (String arr : array) {
            if (arr == null) {
                System.out.println("From file name is empty!");
            }

            String[] operationType = arr.split(",");

            if (operationType[NAME_POSITION].equals("supply")) {
                try {
                    sumSupply += Integer.parseInt(operationType[NUM_POSITION]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number, invalid value entered", e);
                }
            }

            if (operationType[NAME_POSITION].equals("buy")) {
                try {
                    sumBuy += Integer.parseInt(operationType[NUM_POSITION]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number, invalid value entered", e);
                }
            }
        }

        builder = new StringBuilder();
        builder.append("supply,").append(sumSupply).append(System.lineSeparator());
        builder.append("buy,").append(sumBuy).append(System.lineSeparator());
        builder.append("result,").append(sumSupply - sumBuy);

        File fileName = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
        }

    }
}
