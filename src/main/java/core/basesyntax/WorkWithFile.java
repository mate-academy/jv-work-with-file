package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        final String supplyId = "supply";
        final String buyId = "buy";
        final String resultId = "result";
        final int idPosition = 0;
        final int valuePosition = 1;
        File file = new File(fromFileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String currentString = fileReader.readLine();
            while (currentString != null) {
                String[] split = currentString.split(",");
                if (split[idPosition].equals(supplyId)) {
                    supplyAmount += Integer.parseInt(split[valuePosition]);
                }
                if (split[idPosition].equals(buyId)) {
                    buyAmount += Integer.parseInt(split[valuePosition]);
                }
                currentString = fileReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder(supplyId).append(",").append(supplyAmount)
                .append(System.lineSeparator()).append(buyId).append(",").append(buyAmount)
                .append(System.lineSeparator()).append(resultId).append(",")
                .append(supplyAmount - buyAmount);
        writeReport(toFileName, stringBuilder.toString());
    }

    private void writeReport(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file " + toFileName, e);
        }
    }
}
