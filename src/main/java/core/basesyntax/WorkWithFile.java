package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        int supplyTotal = 0;
        int buyTotal = 0;

        StringBuilder readDate = new StringBuilder();
        BufferedWriter writer = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            writer = new BufferedWriter(new FileWriter(toFile));
            String dateLine = reader.readLine();
            while (dateLine != null) {
                readDate.append(dateLine).append(System.lineSeparator());
                dateLine = reader.readLine();
            }

            String[] arrayReadData = readDate.toString().split(System.lineSeparator());
            for (String date : arrayReadData) {
                String[] parts = date.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operationType.equals("supply")) {
                    supplyTotal += amount;
                } else if (operationType.equals("buy")) {
                    buyTotal += amount;
                } else {
                    throw new IllegalArgumentException("Invalid operation type: " + operationType);
                }
            }

            int result = supplyTotal - buyTotal;
            writer.write("supply," + supplyTotal + System.lineSeparator());
            writer.write("buy," + buyTotal + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("can't read file",e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("can't write date into file",e);
            }
        }
    }
}
