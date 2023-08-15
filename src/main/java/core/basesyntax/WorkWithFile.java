package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String finalReport = createReport(data);
        writeToFile(finalReport, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                sb.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        return sb.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        int buyAmount = 0;
        int supplyAmount = 0;
        int result = 0;
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split(",");
            if (splitData[0].equals("buy")) {
                buyAmount += Integer.parseInt(splitData[1]);
            } else if (splitData[0].equals("supply")) {
                supplyAmount += Integer.parseInt(splitData[1]);
            }
        }
        result = supplyAmount - buyAmount;
        report.append("supply").append(",").append(supplyAmount).append("\r\n")
                .append("buy").append(",").append(buyAmount).append("\r\n")
                .append("result").append(",").append(result);
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't open file", e);
        }
    }
}
