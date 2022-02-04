package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splited = value.split(",");
                int parsed = Integer.parseInt(splited[1]);
                if (splited[0].equals(SUPPLY)) {
                    supplyCounter += parsed;
                } else if (splited[0].equals(BUY)) {
                    buyCounter += parsed;
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Cann't read file...", e);
        }
        int result = supplyCounter - buyCounter;
        StringBuilder report = createReport(supplyCounter, buyCounter, result);
        writeReportToFile(report, toFileName);
    }

    private void writeReportToFile(StringBuilder report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private StringBuilder createReport(int supplyCounter, int buyCounter, int result) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY + "," + supplyCounter)
                .append(System.lineSeparator())
                .append(BUY + "," + buyCounter)
                .append(System.lineSeparator())
                .append(RESULT + "," + result);
        return report;
    }
}


