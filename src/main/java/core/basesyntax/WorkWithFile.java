package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String CHARACTER_TO_SPLIT = ",";
    
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readTheDataFile(fromFileName);
        writeDataToFile(lines, toFileName);
    }

    private List<String> readTheDataFile(String fromFileName) {
        List<String> lines;
        try {
            Path path = Paths.get(fromFileName);
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return lines;
    }

    private static int calculateTotal(List<String> lines, String nameAmount) {
        int amount = 0;
        for (String line : lines) {
            String[] parts = line.split(CHARACTER_TO_SPLIT);
            if (parts[0].equals(nameAmount)) {
                amount += Integer.parseInt(parts[1]);
            }
        }
        return amount;
    }

    private static String createReport(List<String> lines) {
        int supply = calculateTotal(lines, SUPPLY);
        int buy = calculateTotal(lines, BUY);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(CHARACTER_TO_SPLIT).append(supply)
                .append(System.lineSeparator()).append(BUY).append(CHARACTER_TO_SPLIT).append(buy)
                .append(System.lineSeparator()).append("result,").append(supply - buy);
        return stringBuilder.toString();
    }

    private void writeDataToFile(List<String> lines, String fileName) {
        String report = createReport(lines);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
