package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int OPERATION_TYPE_POSITION = 0;
    static final int AMOUNT_POSITION = 1;
    public void getStatistic(String fromFileName, String toFileName) {
        String report = processDataFromFile(fromFileName);
        writeToFile(report, toFileName);
    }
    public String processDataFromFile (String fromFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String fileString = reader.readLine();
            while (fileString != null) {
                String[] lineValues = fileString.split(",");
                if (lineValues[OPERATION_TYPE_POSITION].equals("supply")) {
                    supplyCount += Integer.parseInt(lineValues[AMOUNT_POSITION]);
                } else {
                    buyCount += Integer.parseInt((lineValues[AMOUNT_POSITION]));
                }
                fileString = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return createReport(supplyCount, buyCount);
    }
    public String createReport (int supplyCount, int buyCount) {
        StringBuilder report = new StringBuilder();
        int result = supplyCount - buyCount;
        report.append("supply,").append(supplyCount).append(System.lineSeparator());
        report.append("buy,").append(buyCount).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());
        return report.toString();
    }

    public void writeToFile (String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
