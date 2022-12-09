package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class WorkWithFile {
    private final String supplyName = "supply";
    private final String buyName = "buy";
    private int supplySum = 0;
    private int baySum = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeToFile(toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[0].equals(supplyName)) {
                    supplySum += Integer.parseInt(split[1]);
                } else if (split[0].equals(buyName)) {
                    baySum += Integer.parseInt(split[1]);
                }
                value = bufferedReader.readLine();
            }
            result = supplySum - baySum;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return fromFileName;
    }

    private void writeToFile(String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(supplyName).append(",").append(supplySum)
                    .append(System.lineSeparator())
                    .append(buyName).append(",").append(baySum).append(System.lineSeparator())
                    .append("result").append(",").append(result);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
