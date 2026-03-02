package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public String[] getStatistic(String fromFileName, String toFileName) throws Exception {
        if (fromFileName == null || toFileName == null) {
            throw new NullPointerException("No file to read and/or no file to write");
        }

        String[] lines = null;
        StringBuilder result = new StringBuilder();

        try {
            lines = Files.readAllLines(Path.of(fromFileName)).toArray(new String[0]);
            if (lines.length == 0) ;

            int buyAmount = 0;
            int supplyAmount = 0;
            final String ls = System.lineSeparator();

            for (int i = 0; i < lines.length; i++) {
                String toSplit = lines[i];
                String[] splittedLine = toSplit.split(",");
                String operationType = splittedLine[0];
                String amountStr = splittedLine[1];
                int amount = Integer.parseInt(amountStr);

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
            } catch (Exception e) {
                throw new NullPointerException("File is empty");
            }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(result.toString());
        } catch (Exception e) {
            throw new NullPointerException();
        }
    return new String[]{result.toString()};
    }
}

