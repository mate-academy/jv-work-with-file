package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        List<String> report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
    }

    private int calculateTotal(List<String> data, String operationType) {
        int total = 0;
        for (String line : data) {
            String[] eachElement = line.split(COMMA);
            if (eachElement[0].equals(operationType)) {
                total += Integer.parseInt(eachElement[1]);
            }
        }
        return total;
    }

    private List<String> createReport(List<String> dataFromFile) {
        int supplyAmount = calculateTotal(dataFromFile, SUPPLY);
        int buyAmount = calculateTotal(dataFromFile, BUY);
        int result = supplyAmount - buyAmount;
        StringBuilder builder = new StringBuilder();
        final String firstLine = builder.append(SUPPLY).append(COMMA)
                .append(supplyAmount).toString();
        builder.setLength(0);
        final String secondLine = builder.append(BUY).append(COMMA)
                .append(buyAmount).toString();
        builder.setLength(0);
        final String thirdLine = builder.append(RESULT).append(COMMA)
                .append(result).toString();
        builder.setLength(0);
        return Arrays.asList(firstLine, secondLine, thirdLine);
    }

    private void writeToFile(String fileName, List<String> report) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String line : report) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
