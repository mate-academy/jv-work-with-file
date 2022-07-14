package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private StringBuilder stringBuilder = null;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lineArray = readFile(fromFileName).split(System.lineSeparator());
        stringBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        for (String line : lineArray) {
            String[] detailedData = line.split(",");
            if (detailedData[OPERATION_TYPE_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(detailedData[AMOUNT_INDEX]);
            } else if (detailedData[OPERATION_TYPE_INDEX].equals("buy")) {
                buySum += Integer.parseInt(detailedData[AMOUNT_INDEX]);
            }
        }
        stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                        .append("buy,").append(buySum).append(System.lineSeparator())
                        .append("result,").append(supplySum - buySum);
        writeFile(stringBuilder.toString(),toFileName);
    }

    public String readFile(String incomeFile) {
        stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(incomeFile))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + incomeFile, e);
        }
        return stringBuilder.toString();
    }

    public void writeFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
