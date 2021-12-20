package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        rearFromFile(fromFileName);
        String result = createResult(stringBuilder.toString());
        writeToFIle(result, toFileName);
    }

    private void rearFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String createResult(String input) {
        String[] array = input.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        int allAmount = 0;
        for (String i : array) {
            String[] a = i.split(",");
            if (a[0].equals("supply")) {
                supplyAmount += Integer.parseInt(a[1]);
            } else {
                buyAmount += Integer.parseInt(a[1]);
            }
            allAmount = supplyAmount - buyAmount;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(allAmount);
        return stringBuilder.toString();
    }

    private void writeToFIle(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
