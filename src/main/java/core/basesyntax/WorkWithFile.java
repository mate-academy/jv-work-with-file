package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = parseData(getData(fromFileName));
        String report = createReport(data);
        writeReport(toFileName, report);
    }

    private List<String> getData(String fileName) {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return allLines;
    }

    private void writeReport(String fileName, String report) {
        try {
            Files.createFile(Path.of(fileName));
            Files.write(Path.of(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }

    private String createReport(int[] data) {
        int result = data[0] - data[1];
        return "supply," + data[0] + System.lineSeparator()
                + "buy," + data[1] + System.lineSeparator()
                + "result," + result;
    }

    private int[] parseData(List<String> data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : data) {
            String[] typeAndAmount = line.split(",");
            if (typeAndAmount[0].equals("supply")) {
                supplyAmount += Integer.parseInt(typeAndAmount[1]);
            } else {
                buyAmount += Integer.parseInt(typeAndAmount[1]);
            }
        }
        return new int[]{supplyAmount, buyAmount};
    }
}
