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
        int supplyCount = 0;
        int buyCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String fileString = reader.readLine();
            while (fileString != null) {
                String[] lineValues = fileString.split(",");
                switch (lineValues[0]) {
                    case "supply":
                        supplyCount += Integer.parseInt(lineValues[1]);
                        break;
                    default:
                        buyCount += Integer.parseInt((lineValues[1]));
                }
                fileString = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        StringBuilder builder = new StringBuilder();
        int result = supplyCount - buyCount;
        builder.append("supply,").append(supplyCount).append(System.lineSeparator());
        builder.append("buy,").append(buyCount).append(System.lineSeparator());
        builder.append("result,").append(result).append(System.lineSeparator());
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
