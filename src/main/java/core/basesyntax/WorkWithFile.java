package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUYWORD = "buy";
    private static final String SUPPLYWORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int countBuy = 0;
        int countSupply = 0;
        String[] readTextArray = readText(fromFileName).split(System.lineSeparator());
        for (String operations: readTextArray) {
            String[] withoutComma = operations.split(",");
            if (withoutComma[0].equals(BUYWORD)) {
                countBuy += Integer.parseInt(withoutComma[1]);
            } else if (withoutComma[0].equals(SUPPLYWORD)) {
                countSupply += Integer.parseInt(withoutComma[1]);
            }
        }
        int result = countSupply - countBuy;
        String reportedString = report(countBuy, countSupply, result);
        writeText(toFileName, reportedString);
    }

    public String readText(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return stringBuilder.toString();
    }

    private String report(int countBuy, int countSupply, int result) {
        return new StringBuilder(SUPPLYWORD).append(",").append(countSupply)
                .append(System.lineSeparator())
                .append(BUYWORD).append(",").append(countBuy)
                .append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }

    private void writeText(String toFileName, String reportedString) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportedString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write this file" + e);
        }
    }
}
