package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        if (!file.exists() || !file.canWrite()) {
            throw new RuntimeException("Such file does not exist: " + fromFileName);
        }

        int totalSupply = 0;
        int totalBuy = 0;

        try {
            List<String> rows = Files.readAllLines(file.toPath());
            for (String row : rows) {
                String[] parts = row.split(",");
                String operation = parts[0];
                try {
                    int value = Integer.parseInt(parts[1]);

                    if (operation.equals("supply")) {
                        totalSupply += value;
                    } else if (operation.equals("buy")) {
                        totalBuy += value;
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read provided file: " + fromFileName, e);
        }

        int totalResult = totalSupply - totalBuy;

        File finalFile = new File(toFileName);

        File parentDir = finalFile.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            throw new RuntimeException("Unable to create directories for file: " + toFileName);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalFile))) {
            writer.write("supply," + totalSupply + System.lineSeparator());
            writer.write("buy," + totalBuy + System.lineSeparator());
            writer.write("result," + totalResult + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file: " + toFileName, e);
        }
    }
}
