package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dayData = readFile(fromFileName);
        int supplyAmount = countProduct(dayData, "supply");
        int buyAmount = countProduct(dayData, "buy");
        String report = makeReport(supplyAmount, buyAmount);
        writeFile(toFileName, report);
    }

    private List<String> readFile(String fromFileName) {
        List<String> info = null;
        try {
            info = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        return info;
    }

    private int countProduct(List<String> info, String similar) {
        int dayResult = 0;
        for (String line : info) {
            if (line.startsWith(similar)) {
                dayResult += Integer.parseInt(line.substring(line.indexOf(",") + 1));
            }
        }
        return dayResult;
    }

    private String makeReport(int supplyAmount, int buyAmount) {
        StringBuilder dayReport = new StringBuilder();
        dayReport.append("supply,").append(supplyAmount).append(System.lineSeparator());
        dayReport.append("buy,").append(buyAmount).append(System.lineSeparator());
        dayReport.append("result,").append(supplyAmount - buyAmount).append(System.lineSeparator());
        return dayReport.toString();
    }

    private void writeFile(String toFileName, String data) {
        try (FileWriter report = new FileWriter(toFileName, false)) {
            report.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to File");
        }
    }
}

