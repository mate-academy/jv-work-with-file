package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_NAME_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayData = fileReader(fromFileName).split(System.lineSeparator());
        int supplyCount = 0;
        int buyCount = 0;
        for (String data: arrayData) {
            String[] separationData = data.split(",");
            if (separationData[OPERATION_NAME_INDEX].equals("buy")) {
                buyCount += Integer.parseInt(separationData[AMOUNT_INDEX]);
            } else {
                supplyCount += Integer.parseInt(separationData[AMOUNT_INDEX]);
            }
        }
        String[] report = { "supply," + supplyCount,
                "buy," + buyCount,
                "result," + (supplyCount - buyCount)};
        fileWriter(report, toFileName);
    }

    private String fileReader(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lineData = reader.readLine();
            while (lineData != null) {
                stringBuilder.append(lineData).append(System.lineSeparator());
                lineData = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data to file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private void fileWriter(String[] report, String toFileName) {
        for (String reportLine: report) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(toFileName, true))) {
                bufferedWriter.write(reportLine + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file" + toFileName, e);
            }
        }
    }
}
