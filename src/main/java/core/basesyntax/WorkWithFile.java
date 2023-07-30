package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String KEYWORD = "supply";
    private static final int FIRSTINDEX = 0;
    private static final int SECONDINDEX = 1;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        int [] resultNumber = parseNumber(dataFromFile);
        String report = createReport(resultNumber);
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
            throw new RuntimeException("File not found",e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file",e);
        }
        return readStr.toString();
    }

    private int [] parseNumber(String dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        String [] splitLine = dataFromFile.split(",");
        for (int i = 0; i < splitLine.length - 1; i += 2) {
            if (splitLine[i].equals(KEYWORD)) {
                sumSupply += Integer.parseInt(splitLine[i + ONE]);
                //   а здесь тоже нужно менять на константу ^^^^?
            } else {
                sumBuy += Integer.parseInt(splitLine[i + ONE]);
            }
        }
        return new int [] {sumSupply,sumBuy};
    }

    private String createReport(int [] resultNumber) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("supply,")
                .append(resultNumber[FIRSTINDEX])
                .append(System.lineSeparator())
                .append("buy,")
                .append(resultNumber[SECONDINDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(resultNumber[FIRSTINDEX] - resultNumber[SECONDINDEX]);
        return resultStr.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file",e);
        }
    }
}
