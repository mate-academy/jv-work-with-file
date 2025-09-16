package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String SUPPLY_LABEL = "supply";
    private static String BUY_LABEL = "buy";
    private static String RESULT_LABEL = "result";
    private static String DELIMITER = ",";
    private static String NEW_LINE = "\n";

    private int[] readAndAggregate(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int[] result = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            String[] key = line.split(",");
            while (line != null) {
                key = line.split(",");
                if (key[0].equals("supply")) {
                    result[0] += Integer.parseInt(key[1]);
                    line = reader.readLine();
                } else {
                    result[1] += Integer.parseInt(key[1]);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Not able to read a file", e);
        }
        return result;
    }

    private String buildReport(int totalSupply, int totalBuy) {
        String report = "";
        report += SUPPLY_LABEL + DELIMITER + totalSupply + NEW_LINE;
        report += BUY_LABEL + DELIMITER + totalBuy + NEW_LINE;
        report += RESULT_LABEL + DELIMITER + (totalSupply - totalBuy);
        return report;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Not able to write a file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totalResult = readAndAggregate(fromFileName);
        writeToFile(toFileName, buildReport(totalResult[0], totalResult[1]));
    }
}
