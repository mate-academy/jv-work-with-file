package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private static String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        final String Supply = "supply";
        final String Buy = "buy";
        final String Result = "result";
        String[] dataFromFileArray = dataFromFile.split(" ");
        int supplyCount = 0;
        int buyCount = 0;
        int resultCount = 0;

        for (int i = 0; i < dataFromFileArray.length; i++) {
            String[] currentDataArr = dataFromFileArray[i].split(",");
            String nameOfOperation = currentDataArr[0];
            int currentCount = Integer.parseInt(currentDataArr[1]);

            switch (nameOfOperation) {
                case Supply:
                    supplyCount += currentCount;
                    break;
                case Buy:
                    buyCount += currentCount;
                    break;
                default:
            }
        }

        resultCount = supplyCount - buyCount;

        return (
                Supply + "," + supplyCount + System.lineSeparator()
                + Buy + "," + buyCount + System.lineSeparator()
                + Result + "," + resultCount + System.lineSeparator()
            );
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
