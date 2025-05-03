package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int AMOUNT_POS = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayFromFile = dataFromFile(fromFileName);
        int sumBuy = 0;
        int sumSupply = 0;
        for (String line : arrayFromFile) {
            if (line.contains(BUY)) {
                sumBuy += Integer.parseInt(line.split(",")[AMOUNT_POS]);
            } else if (line.contains(SUPPLY)) {
                sumSupply += Integer.parseInt(line.split(",")[AMOUNT_POS]);
            }
        }
        String report = getReport(sumSupply, sumBuy);
        dataToFile(toFileName, report);
    }

    private String[] dataFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void dataToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFile, e);
        }
    }

    private String getReport(int sumSupply, int sumBuy) {
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(",").append(sumSupply).append(System.lineSeparator());
        sb.append(BUY).append(",").append(sumBuy).append(System.lineSeparator());
        sb.append(RESULT).append(",").append(sumSupply - sumBuy).append(System.lineSeparator());
        return sb.toString();
    }
}
