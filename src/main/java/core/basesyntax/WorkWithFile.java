package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null) {
            System.out.println("Source file doesn't exists");
            return;
        }
        if (toFileName == null) {
            System.out.println("Destination file doesn't exists");
            return;
        }

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

        String[] splitValues = builder.toString().split(System.lineSeparator());

        for (String value : splitValues) {
            if (value == null) {
                System.out.println("From file name is empty!");
            }

            assert value != null;
            String[] items = value.split(",");

            if (items[0].equals("supply")) {
                try {
                    sumSupply += Integer.parseInt(items[1]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number, invalid value entered", e);
                }
            }

            if (items[0].equals("buy")) {
                try {
                    sumBuy += Integer.parseInt(items[1]);
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
