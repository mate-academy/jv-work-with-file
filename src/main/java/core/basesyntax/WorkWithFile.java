package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String supply = "supply";
    private final String buy = "buy";
    private final String result = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        int indexOfName = 0;
        int indexOfCount = 1;
        File file = new File(fromFileName);
        String[] splittedData;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();

            while (value != null) {
                splittedData = value.split(",");
                if (splittedData[indexOfName].equals(supply)) {
                    supplyCount += Integer.parseInt(splittedData[indexOfCount]);
                } else {
                    buyCount += Integer.parseInt(splittedData[indexOfCount]);
                }
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find such file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return supplyCount + " " + buyCount;
    }

    private String createReport(String dataFromFile) {
        int indexOfSupply = 0;
        int indexOfBuy = 1;
        String[] splittedData = dataFromFile.split(" ");
        int supplyCount = Integer.parseInt(splittedData[indexOfSupply]);
        int buyCount = Integer.parseInt(splittedData[indexOfBuy]);
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
