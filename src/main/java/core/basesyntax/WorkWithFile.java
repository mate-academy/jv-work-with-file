package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String TEXT_SUPPLY = "supply";
    private static final String TEXT_BUY = "buy";
    private static final String TEXT_RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> output = readFile(fromFileName);
        String result = getResult(output);
        writeToFile(result, toFileName);
    }

    private List<String> readFile(String fromFileName) {

        File file = new File(fromFileName);
        List<String> output;
        try {
            output = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return output;
    }

    private String getResult(List<String> output) {
        int supplyAll = 0;
        int buyAll = 0;
        for (String line : output) {
            String[] linePars = line.split(SEPARATOR);
            switch (linePars[INDEX_OPERATION]) {
                case (TEXT_SUPPLY):
                    supplyAll += Integer.parseInt(linePars[INDEX_SUM]);
                    break;
                case (TEXT_BUY):
                    buyAll += Integer.parseInt(linePars[INDEX_SUM]);
                    break;
                default:
                    break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TEXT_SUPPLY).append(SEPARATOR).append(supplyAll).append(System.lineSeparator())
                .append(TEXT_BUY).append(SEPARATOR).append(buyAll).append(System.lineSeparator())
                .append(TEXT_RESULT).append(SEPARATOR).append(supplyAll - buyAll).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(text);
        } catch (Exception e) {
            throw new RuntimeException("Can`t write to file " + toFileName, e);
        }
    }
}
