package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder result = new StringBuilder();
        int supplyCount = 0;
        int buyCount = 0;
        try {
            String fileContent = Files.readString(Path.of(fromFileName));
            //I was trying to split with System.lineSeparator() but it didn't work by some reason
            String[] fileSeparatedLines = fileContent.split("\\r?\\n");
            for (String line : fileSeparatedLines) {
                String[] lineSeparatedWords = line.split(",");
                if (lineSeparatedWords[0].equals("supply")) {
                    supplyCount += Integer.parseInt(lineSeparatedWords[1]);
                } else if (lineSeparatedWords[0].equals("buy")) {
                    buyCount += Integer.parseInt(lineSeparatedWords[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        result.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(supplyCount - buyCount);
        try {
            Files.writeString(Path.of(toFileName), result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
