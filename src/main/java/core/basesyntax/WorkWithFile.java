package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public String[] getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || toFileName == null) {
            throw new NullPointerException("No file to read and/or no file to write");
        }

        StringBuilder result = new StringBuilder();
        final String ls = System.lineSeparator();

        try {
            String[] lines = Files.readAllLines(Path.of(fromFileName)).toArray(new String[0]);
            int buyAmount = 0;
            int supplyAmount = 0;

            for (int i = 0; i < lines.length; i++) {
                String[] splittedLine = lines[i].split(",");
                String operationType = splittedLine[0];
                int amount = Integer.parseInt(splittedLine[1]);

                if (operationType.equals("buy")) {
                    buyAmount += amount;
                } else if (operationType.equals("supply")) {
                    supplyAmount += amount;
                }
            }

            int difference = supplyAmount - buyAmount;
            result.append("supply,")
                    .append(supplyAmount)
                    .append(ls)
                    .append("buy,")
                    .append(buyAmount)
                    .append(ls)
                    .append("result,")
                    .append(difference)
                    .append(ls);
        } catch (IOException e) {
            throw new RuntimeException("Can't read input file: " + fromFileName, e);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Bad input format in file: " + fromFileName, e);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(result.toString());
        } catch (Exception e) {
            throw new NullPointerException();
        }
        return new String[]{result.toString()};
    }
}

