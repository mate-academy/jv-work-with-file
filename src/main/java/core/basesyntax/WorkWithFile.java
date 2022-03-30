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
        String data = readFromFile(fromFileName);
        writeToFile(toFileName, data);
    }

    private String readFromFile(String fromFileName) {
        int buyScore = 0;
        int supplyScore = 0;
        int resultScore = 0;
        StringBuilder report = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitted = line.split(",");
                switch (splitted[0]) {
                    case "buy":
                        buyScore += Integer.valueOf(splitted[1]);
                        break;
                    default:
                        supplyScore += Integer.valueOf(splitted[1]);
                }
                line = bufferedReader.readLine();
            }
            resultScore = supplyScore - buyScore;
        } catch (IOException e) {
            throw new RuntimeException("Cant`t read data from file", e);
        }
        return report.append(SUPPLY).append(",").append(supplyScore).append(System.lineSeparator())
                .append(BUY).append(",").append(buyScore).append(System.lineSeparator())
                .append(RESULT).append(",").append(resultScore).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
