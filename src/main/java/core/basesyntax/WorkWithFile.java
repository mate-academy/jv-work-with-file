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
        File file = new File(fromFileName);
        StringBuilder reportBuilder = new StringBuilder();
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
            reportBuilder.append(SUPPLY + ",")
                    .append(supplyInt)
                    .append(System.lineSeparator())
                    .append(BUY + ",")
                    .append(buyInt)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(result);
        } catch (IOException e) {
            throw new RuntimeException("file not found", e);
        }
        String[] lineSplitted = reportBuilder.toString().split(System.lineSeparator());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String s : lineSplitted) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
