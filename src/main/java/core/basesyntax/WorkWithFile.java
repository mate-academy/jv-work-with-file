package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(new File(fromFileName)));
    }

    private StringBuilder readFromFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value;
            int supplyInt = 0;
            int buyInt = 0;
            int result = 0;
            while ((value = bufferedReader.readLine()) != null) {
                String[] splittedArr = value.split(CSV_SEPARATOR);
                if (splittedArr[0].equals(SUPPLY)) {
                    supplyInt += Integer.parseInt(splittedArr[1]);
                } else if (splittedArr[0].equals(BUY)) {
                    buyInt += Integer.parseInt(splittedArr[1]);
                }
            }
            result = supplyInt - buyInt;
            return createReportBuilder(supplyInt, buyInt, result);
        } catch (IOException e) {
            throw new RuntimeException("file not found", e);
        }
    }

    private StringBuilder createReportBuilder(int supplyInt, int buyInt, int result) {
        return new StringBuilder().append(SUPPLY + ",")
                .append(supplyInt)
                .append(System.lineSeparator())
                .append(BUY + ",")
                .append(buyInt)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
    }

    private boolean writeToFile(String toFileName, StringBuilder reportBuilder) {
        if (reportBuilder == null) {
            return false;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
        return true;
    }
}
