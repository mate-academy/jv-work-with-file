package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(crateReport(readFromFile(fromFileName)));

        } catch (IOException e) {
            throw new RuntimeException(e + " Can not write to file " + toFileName);
        }
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append("/");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e + " Can not read from file " + fromFileName);
        }
    }

    private String crateReport(String readFromFile) {
        String[] fileLines = readFromFile.split("/");
        int supplySum = 0;
        int buySum = 0;
        for (String fileLine : fileLines) {
            if (fileLine.contains("supply")) {
                String[] supplyLine = fileLine.split(",");
                supplySum += Integer.parseInt(supplyLine[1]);
            } else {
                String[] buyLine = fileLine.split(",");
                buySum += Integer.parseInt(buyLine[1]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder("supply,");
        return stringBuilder.append(supplySum)
                .append(System.lineSeparator()).append("buy,")
                .append(buySum).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }
}
