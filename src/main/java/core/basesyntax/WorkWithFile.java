package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {

        int supplyAmount = 0;
        int buyAmount = 0;

        File myFile = new File(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(myFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                System.out.println("The line is empty");
                continue;
            }
            String[] parts = line.split(DELIMITER);
            if (parts.length != 2) {
                throw new RuntimeException("Malformed line: " + line);
            }
            String operation = parts[0].trim();
            int amount = Integer.parseInt(parts[1].trim());
            if (operation.equals(SUPPLY)) {
                supplyAmount += amount;
            } else if (operation.equals(BUY)) {
                buyAmount += amount;
            }
        }
        int result = supplyAmount - buyAmount;
        StringBuilder resulting = new StringBuilder();
        resulting.append("supply,").append(supplyAmount).append("\n")
                .append("buy,").append(buyAmount).append("\n")
                .append("result,").append(result).append("\n");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(resulting));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
