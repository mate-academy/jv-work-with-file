package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int sumSupply = 0;
            int sumBuy = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                String operation = split[0];
                int digit = Integer.parseInt(split[1]);

                if (operation.equals(SUPPLY)) {
                    sumSupply += digit;
                } else {
                    sumBuy += digit;
                }
            }
            return new String[]{String.valueOf(sumSupply), String.valueOf(sumBuy)};
        } catch (IOException exception) {
            throw new RuntimeException("Can`t read data from file " + fromFileName, exception);
        }
    }

    private String createReport(String[] resultInfo) {
        int supply = Integer.parseInt(resultInfo[0]);
        int buy = Integer.parseInt(resultInfo[1]);
        int result = supply - buy;

        return new StringBuilder()
                .append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException exception) {
            throw new RuntimeException("Can`t write data to file " + toFileName, exception);
        }
    }
}
