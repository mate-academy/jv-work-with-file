package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName);
        writeDataToFile(toFileName, totalStatCalculation(readDataFromFile(fromFileName)));
    }

    private String[] readDataFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                builder.append(currentLine).append(System.lineSeparator());
                currentLine = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String totalStatCalculation(String[] splitStrings) {
        int supplySum = 0;
        int buySum = 0;
        for (String currentString : splitStrings) {
            String[] twoValues = currentString.split(",");
            if (twoValues[0].equals("supply")) {
                supplySum += Integer.parseInt(twoValues[1]);
            }
            if (twoValues[0].equals("buy")) {
                buySum += Integer.parseInt(twoValues[1]);
            }
        }
        int result = supplySum - buySum;

        return new StringBuilder().append("supply,")
                .append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private void writeDataToFile(String toFileName, String totalStatCalculation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(totalStatCalculation);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file:" + toFileName, e);
        }
    }
}
