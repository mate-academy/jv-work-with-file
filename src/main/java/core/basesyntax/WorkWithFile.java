package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String KEYWORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int [] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName,report);
    }

    int [] readFromFile(String fromFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] splitLine = readLine.split(",");
                if (splitLine[0].equals(KEYWORD)) {
                    sumSupply += Integer.parseInt(splitLine[1]);
                } else {
                    sumBuy += Integer.parseInt(splitLine[1]);
                }
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found",e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file",e);
        }
        return new int [] {sumSupply,sumBuy};
    }

    String createReport(int [] dataFromFile) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("supply,")
                .append(dataFromFile[0])
                .append(System.lineSeparator())
                .append("buy,")
                .append(dataFromFile[1])
                .append(System.lineSeparator())
                .append("result,")
                .append(dataFromFile[0] - dataFromFile[1]);
        return resultStr.toString();
    }

    void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file",e);
        }
    }
}
