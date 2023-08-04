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

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayFromFile = getDataFormCsvFile(fromFileName);
        int sumBuy = 0;
        int sumSupply = 0;
        for (String line : arrayFromFile) {
            if (line.contains(BUY)) {
                sumBuy += Integer.parseInt(line.split(",")[1]);
            } else if (line.contains(SUPPLY)) {
                sumSupply += Integer.parseInt(line.split(",")[1]);
            }
        }
        printResultToCsvFile(toFileName, sumSupply, sumBuy);
    }

    private String[] getDataFormCsvFile(String fromFileName) {
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

    private void printResultToCsvFile(String toFileName, int sumSupply, int sumBuy) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(SUPPLY + "," + sumSupply + System.lineSeparator());
            bufferedWriter.write(BUY + "," + sumBuy + System.lineSeparator());
            bufferedWriter.write(RESULT + "," + (sumSupply - sumBuy) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFile, e);
        }
    }
}
