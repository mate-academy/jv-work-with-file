package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int sumBuy = 0;
        int sumSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length >= 2) {
                    String type = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());

                    if (type.equals("buy")) {
                        sumBuy += value;
                    } else if (type.equals("supply")) {
                        sumSupply += value;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File nor found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        File newFile = new File(toFileName);
        try {
            FileWriter fileWriter = new FileWriter(newFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Files.write(newFile.toPath(),
                    ("supply," + sumSupply + "\n"
                            + "buy," + sumBuy + "\n"
                            + "result," + (sumSupply - sumBuy))
                            .getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
