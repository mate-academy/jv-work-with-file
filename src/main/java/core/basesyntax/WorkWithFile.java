package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader dataFromFile = readFile(fromFileName)) {
            String report = createReport(calculateSummary(dataFromFile));
            writeReportToFile(report, toFileName);
        } catch (IOException e) {
            System.out.println("Can`t read data from file " + fromFileName + e);
        }
    }

    private BufferedReader readFile(String fromFileName) throws FileNotFoundException {
        return new BufferedReader(new FileReader(fromFileName));
    }

    private int[] calculateSummary(BufferedReader reportReader) throws IOException {
        int supply = 0;
        int buy = 0;
        String line = reportReader.readLine();

        while (line != null) {
            if (line.startsWith(SUPPLY)) {
                String supplyAmount = line.substring(SUPPLY.length() + DELIMITER.length());
                supply += Integer.parseInt(supplyAmount);
            } else if (line.startsWith(BUY)) {
                String buyAmount = line.substring(BUY.length() + DELIMITER.length());
                buy += Integer.parseInt(buyAmount);
            }
            line = reportReader.readLine();
        }

        int result = supply - buy;
        return new int[] {supply, buy, result};
    }

    private String createReport(int[] summary) {
        return new StringBuilder()
                .append(SUPPLY.toLowerCase())
                .append(DELIMITER)
                .append(summary[0])
                .append(System.lineSeparator())
                .append(BUY.toLowerCase())
                .append(DELIMITER)
                .append(summary[1])
                .append(System.lineSeparator())
                .append(RESULT.toLowerCase())
                .append(DELIMITER)
                .append(summary[2])
                .toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Can`t write to file" + toFileName + e);
        }
    }
}
