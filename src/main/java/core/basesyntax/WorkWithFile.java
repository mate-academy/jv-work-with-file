package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final int SUM_SUPPLY_INDEX = 0;
    private static final int SUM_BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        int [] totalResult = calculateResult(dataFromFile);
        String report = createReport(totalResult);
        writeToFile(toFileName,report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder readStr = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                readStr.append(readLine).append(",");
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName,e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file " + fromFileName,e);
        }
        return readStr.toString();
    }

    private int [] calculateResult(String dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        String [] splitLine = dataFromFile.split(",");
        for (int i = 0; i < splitLine.length - 1; i += 2) {
            if (splitLine[i].equals(OPERATION_SUPPLY)) {
                sumSupply += Integer.parseInt(splitLine[i + 1]);
            } else {
                sumBuy += Integer.parseInt(splitLine[i + 1]);
            }
        }
        return new int [] {sumSupply,sumBuy};
    }

    private String createReport(int [] totalResult) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("supply,")
                .append(totalResult[SUM_SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append("buy,")
                .append(totalResult[SUM_BUY_INDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(totalResult[SUM_SUPPLY_INDEX] - totalResult[SUM_BUY_INDEX]);
        return resultStr.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + toFileName,e);
        }
    }
}
