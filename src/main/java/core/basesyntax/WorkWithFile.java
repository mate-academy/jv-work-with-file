package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName);
        String report = createReport(lines);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {

        File inputFile = new File(fromFileName);
        Path inputFilePath = Path.of(inputFile.toURI());
        List<String> lines;

        try {
            lines = Files.readAllLines(inputFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Can't read that file named: " + fromFileName, e);
        }
        System.out.println(lines);
        return lines.toArray(new String[]{});
    }

    private String createReport(String[] lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operation.equals("supply")) {
                supplyTotal += amount;
            } else if (operation.equals("buy")) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;

        return "supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String report, String toFileName) {
        File outputFile = new File(toFileName);
        Path outputFilePath = Path.of(outputFile.toURI());

        try {
            Files.writeString(outputFilePath, report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file named: " + toFileName, e);
        }
    }
}
