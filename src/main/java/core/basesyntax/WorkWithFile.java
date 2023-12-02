package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String supply = "supply";
    private final String buy = "buy";
    private final String result = "result";
    private final int indexOfName = 0;
    private final int indexOfCount = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return builder.toString();
    }

    private String createReport(String dataFromFile) {
        String[] splittedStrings = dataFromFile.split(" ");
        String[] splittedData;
        int supplyCount = 0;
        int buyCount = 0;

        for (String data: splittedStrings) {
            splittedData = data.split(",");
            if (splittedData[indexOfName].equals(supply)) {
                supplyCount += Integer.parseInt(splittedData[indexOfCount]);
            } else {
                buyCount += Integer.parseInt(splittedData[indexOfCount]);
            }
        }

        int resultCount = supplyCount - buyCount;
        StringBuilder reportMessage = new StringBuilder();
        reportMessage.append(supply + "," + supplyCount).append(System.lineSeparator());
        reportMessage.append(buy + "," + buyCount).append(System.lineSeparator());
        reportMessage.append(result + "," + resultCount);

        return reportMessage.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
