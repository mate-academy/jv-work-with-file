package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_LABEL = "supply";
    private static final String BUY_LABEL = "buy";
    private static final String RESULT_LABEL = "result";
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";

    private int[] readAndAggregate(String fromFileName) {
        int[] result = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            String[] key = new String[2];
            while (line != null) {
                if (key.length == 2) {
                    key = line.split(DELIMITER);
                    if (key[0].equals(SUPPLY_LABEL)) {
                        result[0] += Integer.parseInt(key[1]);
                        line = reader.readLine();
                    } else if (key[0].equals(BUY_LABEL)) {
                        result[1] += Integer.parseInt(key[1]);
                        line = reader.readLine();
                    } else {
                        throw new RuntimeException("Wrong word " + line + "in file "
                                + fromFileName);
                    }
                } else {
                    throw new RuntimeException("Wrong line " + line + "in file " + fromFileName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Not able to read file: " + fromFileName, e);
        }
        return result;
    }

    private String buildReport(int totalSupply, int totalBuy) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_LABEL + DELIMITER).append(totalSupply).append(NEW_LINE);
        report.append(BUY_LABEL + DELIMITER).append(totalBuy).append(NEW_LINE);
        report.append(RESULT_LABEL + DELIMITER).append(totalSupply - totalBuy);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Not able to write in a file: " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totalResult = readAndAggregate(fromFileName);
        writeToFile(toFileName, buildReport(totalResult[0], totalResult[1]));
    }
}
