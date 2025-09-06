package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";
    private static final int INDEX_OF_SUPPLY = 0;
    private static final int INDEX_OF_BUY = 1;
    private static final int INDEX_OF_RESULT = 2;

    public String getStatistic(String fromFileName, String toFileName) {
        StringBuilder answer = new StringBuilder();

        int[] calculation = readAndCalculate(fromFileName);

        answer.append("supply,").append(calculation[INDEX_OF_SUPPLY]).append(System.lineSeparator())
                .append("buy,").append(calculation[INDEX_OF_BUY]).append(System.lineSeparator())
                .append("result,").append(calculation[INDEX_OF_RESULT]);

        createTheReport(toFileName, answer.toString());
        return answer.toString();
    }

    private int[] readAndCalculate(String fromFileName) {
        int[] result = new int[3]; // totalSupply and totalBuy and result

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(DELIMITER);

                if (data.length != 2){
                    throw new RuntimeException("Invalid input format in line: " + line);
                }

                String type = data[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(data[1].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid amount in line: " + line, e);
                }
                if (type.equals(SUPPLY)) {
                    result[0] += amount;
                } else if (type.equals(BUY)) {
                    result[1] += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input file: " + fromFileName, e);
        }
        result[2] = result[0] - result[1];
        return result;
    }

    private void createTheReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write output file: " + toFileName, e);
        }
    }
}
