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

    public void getStatistic(String fromFileName, String toFileName) {
        int[] processedData = readFile(fromFileName);
        writeFile(toFileName, getResultToWrite(processedData));
    }

    private int[] readFile(String fileName) {
        String readString;
        int[] totalSupplyAndBuy = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            readString = bufferedReader.readLine();
            while (readString != null) {
                totalSupplyAndBuy = dataProcessing(readString, totalSupplyAndBuy);
                readString = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file" + fileName, e);
        }
        return totalSupplyAndBuy;
    }

    private void writeFile(String fileName, String dataToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + fileName, e);
        }
    }

    private int[] dataProcessing(String data, int[] totalSupplyAndBuy) {
        String[] processingData = data.split(",");
        switch (processingData[OPERATION_TYPE_INDEX]) {
            case ("supply"):
                totalSupplyAndBuy[SUPPLY_INDEX] = totalSupplyAndBuy[SUPPLY_INDEX]
                        + Integer.parseInt(processingData[AMMOUNT_INDEX]);
                break;
            case ("buy"):
            default:
                totalSupplyAndBuy[BUY_INDEX] = totalSupplyAndBuy[BUY_INDEX]
                        + Integer.parseInt(processingData[AMMOUNT_INDEX]);
                break;
        }
        return totalSupplyAndBuy;
    }

    private String getResultToWrite(int[] processedData) {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("supply,")
                .append(processedData[SUPPLY_INDEX])
                .append(System.lineSeparator()).append("buy,")
                .append(processedData[BUY_INDEX])
                .append(System.lineSeparator()).append("result,")
                .append(processedData[SUPPLY_INDEX] - processedData[BUY_INDEX])
                .append(System.lineSeparator());
        return resultStringBuilder.toString();
    }
}
