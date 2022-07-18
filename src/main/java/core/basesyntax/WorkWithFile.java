package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMMOUNT_INDEX = 1;
    private static final byte SUPPLY_INDEX = 0;
    private static final byte BUY_INDEX = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, createReport(calculate(readFile(fromFileName))));
    }

    private String readFile(String fileName) {
        StringBuilder readData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                readData.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file" + fileName, e);
        }
        return readData.toString();
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + fileName, e);
        }
    }

    private int[] calculate(String data) {
        int[] totalSupplyAndBuy = new int[2];
        String[] processingData = data.split(System.lineSeparator());
        for (String line : processingData) {
            String[] calculateData = line.split(SEPARATOR);
            switch (calculateData[OPERATION_TYPE_INDEX]) {
                case "supply":
                    totalSupplyAndBuy[SUPPLY_INDEX] = totalSupplyAndBuy[SUPPLY_INDEX]
                            + Integer.parseInt(calculateData[AMMOUNT_INDEX]);
                    break;
                case "buy":
                default:
                    totalSupplyAndBuy[BUY_INDEX] = totalSupplyAndBuy[BUY_INDEX]
                            + Integer.parseInt(calculateData[AMMOUNT_INDEX]);
                    break;
            }
        }
        return totalSupplyAndBuy;
    }

    private String createReport(int[] result) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,")
                .append(result[SUPPLY_INDEX])
                .append(System.lineSeparator()).append("buy,")
                .append(result[BUY_INDEX])
                .append(System.lineSeparator()).append("result,")
                .append(result[SUPPLY_INDEX] - result[BUY_INDEX])
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }
}
