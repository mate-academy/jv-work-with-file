package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        String createReportFull = readFromFile(fromFileName);
        String createReport = createReport(createReportFull);
        writeToFile(toFileName, createReport);
    }

    public String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String createReportFull) {

        String operationSupply = "supply";
        String operationBuy = "buy";
        int summaSupply = 0;
        int summaBuy = 0;
        int summaResult;
        int operationIndex = 0;
        int amountIndex = 1;
        String operation;
        int parseInt;

        String[] arrayFromFileName = createReportFull.split("\n");
        for (String record : arrayFromFileName) {
            operation = record.split(",")[operationIndex];
            parseInt = parseInt(record.split(",")[amountIndex]);

            if (operation.equals(operationSupply)) {
                summaSupply += parseInt;
            }
            if (operation.equals(operationBuy)) {
                summaBuy += parseInt;
            }
        }

        StringBuilder mainString = new StringBuilder();
        String comma = ",";
        String divide = "\n";
        summaResult = summaSupply - summaBuy;
        mainString.append(operationSupply).
                append(comma).append(summaSupply).
                append(divide).append(operationBuy).
                append(comma).append(summaBuy).
                append(divide).append("result").
                append(comma).append(summaResult);

        return mainString.toString();
    }

    public void writeToFile(String toFileName, String createReport) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file ", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }
}
