package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine;
            int supplySum = 0;
            int buySum = 0;
            int result = 0;
            while ((readLine = bufferedReader.readLine()) != null) {
                String[] splitArr = readLine.split(CSV_SEPARATOR);
                if (splitArr[0].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(splitArr[1]);
                } else if (splitArr[0].equals(BUY)) {
                    buySum += Integer.parseInt(splitArr[1]);
                }
            }
            result = supplySum - buySum;
            return createReport(supplySum, buySum, result);
        } catch (IOException e) {
            throw new RuntimeException("file not found", e);
        }
    }

    private String createReport(int supplyInt, int buyInt, int result) {
        return SUPPLY + ","
                + supplyInt
                + System.lineSeparator()
                + BUY
                + ","
                + buyInt
                + System.lineSeparator()
                + "result,"
                + result;
    }

    private boolean writeToFile(String toFileName, String report) {
        if (report == null) {
            return false;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
        return true;
    }
}
