package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, processDataForReport(readFromFileAndHoldData(fromFileName)));
    }

    private StringBuilder readFromFileAndHoldData(String fromFileName) {
        StringBuilder rawData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                rawData.append(line).append(",");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return rawData;
    }

    private int[] processDataForReport(StringBuilder rawData) {
        int supply = 0;
        int buy = 0;
        String[] preReportData = rawData.toString().split(",");
        for (int i = 0; i < preReportData.length - 1; i += 2) {
            if (preReportData[i].equals("supply")) {
                supply += Integer.parseInt(preReportData[i + 1]);
            } else if (preReportData[i].equals("buy")) {
                buy += Integer.parseInt(preReportData[i + 1]);
            }
        }
        int result = supply - buy;
        return new int[]{supply, buy, result};
    }

    private void writeToFile(String toFileName, int[] supplyAndBuyData) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        if (!file.exists()) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
                bufferedWriter.write(Constants.SUPPLY_MSG + supplyAndBuyData[0]);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(Constants.BUY_MSG + supplyAndBuyData[1]);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(Constants.RESULT_MSG + supplyAndBuyData[2]);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException("Can't close BufferedWriter", e);
                    }
                }
            }
        }
    }
}
