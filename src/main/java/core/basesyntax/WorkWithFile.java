package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final char DELIMETER = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }

    private String readFromFile(String fromFileName) {
        int sumBuy = 0;
        int sumSupply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String workingString = bufferedReader.readLine();
            while (workingString != null) {
                if (workingString.substring(0, workingString.indexOf(DELIMETER))
                        .equals("supply")) {
                    sumSupply += Integer.parseInt(
                            workingString.substring(workingString.indexOf(DELIMETER) + 1));
                } else {
                    sumBuy += Integer.parseInt(
                            workingString.substring(workingString.indexOf(DELIMETER) + 1));
                }
                workingString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
        stringBuilder.append(("supply,")).append(sumSupply)
                .append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(sumSupply - sumBuy);
        return stringBuilder.toString();
    }
}
