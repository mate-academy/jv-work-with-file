package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(Files.readString(Path.of(fromFileName))).append(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file " + fromFileName, e);
        }
        String[] dataArray = builder.toString().split(System.lineSeparator());
        for (int i = 0; i < dataArray.length; i++) {
            String[] oneLine = dataArray[i].split(",");
            if (oneLine[0].equals("supply")) {
                supplyCounter += Integer.parseInt(oneLine[1]);
            }
            if (oneLine[0].equals("buy")) {
                buyCounter += Integer.parseInt(oneLine[1]);
            }
        }
        builder.setLength(0);
        builder.append("supply,").append(supplyCounter).append(System.lineSeparator())
                .append("buy,").append(buyCounter).append(System.lineSeparator())
                .append("result,").append(supplyCounter - buyCounter);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
