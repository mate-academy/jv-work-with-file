package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int AMOUNT_INDEX = 1;
    private static final int ACTION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        String report = createReport(lines);
        writeReportToFile(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from " + fromFileName, e);
        }
        return lines;
    }

    private String createReport(List<String> lines) {
        int resultSupply = 0;
        int resultBuy = 0;

        for (String dataElement : lines) {
            String[] arrayDataSplit = dataElement.split(",");

            if (arrayDataSplit.length == 2) {
                String action = arrayDataSplit[ACTION_INDEX].trim();
                int amount = Integer.parseInt(arrayDataSplit[AMOUNT_INDEX].trim());
                if (action.equals(SUPPLY)) {
                    resultSupply += amount;
                } else if (action.equals(BUY)) {
                    resultBuy += amount;
                }
            }
        }
        int result = resultSupply - resultBuy;
        return SUPPLY + "," + resultSupply + System.lineSeparator()
                + BUY + "," + resultBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }
}
