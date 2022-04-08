package core.basesyntax;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int buySum = 0;
        int supplySum = 0;
        try (FileReader fileReader = new FileReader(file)) {
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String value = reader.readLine();
                while (value != null) {
                    String[] tempSplit = value.split(",");
                    int tempValue = Integer.parseInt(tempSplit[1]);
                    if (tempSplit[0].equals("buy")) {
                        buySum += tempValue;
                    } else {
                        supplySum += tempValue;
                    }
                    value = reader.readLine();
                }
            } catch (FileNotFoundException f) {
                throw new RuntimeException("can not open file", f);
            }
        } catch (IOException e) {
            throw new RuntimeException("can not create BufferedReader", e);
        }
        int result = supplySum - buySum;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buySum).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);

        File resultFile = new File(toFileName);
        try {
            Files.write(resultFile.toPath(),
                    stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
