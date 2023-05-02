package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                if (data[OPERATION_INDEX].equals("buy")) {
                    buySum += Integer.parseInt(data[AMOUNT_INDEX]);
                } else if (data[OPERATION_INDEX].equals("supply")) {
                    supplySum += Integer.parseInt(data[AMOUNT_INDEX]);
                } else {
                    throw new RuntimeException("Unknown type operation - " + data[OPERATION_INDEX]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found:" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:" + fromFileName, e);
        }
        writeResultToFile(toFileName, createReport(supplySum, buySum));
    }

    private void writeResultToFile(String fileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file :" + fileName, e);
        }
    }

    private String createReport(int supplySum, int buySum) {
        StringBuilder builder = new StringBuilder();
        String result = builder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum).toString();
        return result;
    }
}
