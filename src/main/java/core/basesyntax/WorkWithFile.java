package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }

        String data = builder.toString();
        String[] arrayData = data.split(System.lineSeparator());
        int supplyAmout = 0;
        int buyAmout = 0;

        for (int i = 0; i < arrayData.length; i++) {
            String[] temp = arrayData[i].split(",");
            if (temp[0].equals("supply")) {
                supplyAmout += Integer.parseInt(temp[1]);
            }
            if (temp[0].equals("buy")) {
                buyAmout += Integer.parseInt(temp[1]);
            }
        }

        StringBuilder builderResult = new StringBuilder();
        builderResult.append("supply")
                .append(",")
                .append(supplyAmout)
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buyAmout)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(supplyAmout - buyAmout);
        byte[] result = builderResult.toString().getBytes();

        try {
            Files.write(Path.of(toFileName), result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
