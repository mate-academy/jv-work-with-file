package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        File sourceFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Source file doesn't exists or not readable", e);
        }

        String[] splitValues = builder.toString().split(System.lineSeparator());
        for (String value : splitValues) {
            String[] items = value.split(",");

            if (items[0].equals("supply")) {
                supplySum += Integer.parseInt(items[1]);
            }

            if (items[0].equals("buy")) {
                buySum += Integer.parseInt(items[1]);
            }
        }

        builder = new StringBuilder();
        builder.append("supply,").append(supplySum).append(System.lineSeparator());
        builder.append("buy,").append(buySum).append(System.lineSeparator());
        builder.append("result,").append(supplySum - buySum);

        File destinationFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Destination file doesn't exists or not writable", e);
        }
    }
}
