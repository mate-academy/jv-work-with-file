package core.basesyntax;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        String[] file = readFromFile(fromFileName);
        for (String line : file) {
            String[] splitFile = line.split(",");
            if (splitFile[0].equals("buy")) {
                buySum += Integer.parseInt(splitFile[1]);
            }
            if (splitFile[0].equals("supply")) {
                supplySum += Integer.parseInt(splitFile[1]);
            }
        }
        int result = supplySum - buySum;
        writeToFile(buySum,supplySum,result,toFileName);
    }

    private String[] readFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file", e);
        }
    }

    private void writeToFile(int supplySum, int buySum, int result, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("supply,").append(buySum).append(System.lineSeparator())
                .append("buy,").append(supplySum).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        try {
            Files.writeString(Paths.get(path), stringBuilder.toString(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("Cant write data from file", e);
        }
    }
}
