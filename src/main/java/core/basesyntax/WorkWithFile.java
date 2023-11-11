package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName);
        }
        String [] dataFromFileName = stringBuilder.toString().split(System.lineSeparator());
        int sumOfSupplies = 0;
        int sumOfBuying = 0;
        for (String row : dataFromFileName) {
            String [] rowArray = row.split(",");
            if (rowArray[OPERATION_TYPE_INDEX].equals("supply")) {
                String supply = row.split(",")[AMOUNT_INDEX];
                sumOfSupplies += Integer.parseInt(supply);
            } else {
                String buy = row.split(",")[AMOUNT_INDEX];
                sumOfBuying += Integer.parseInt(buy);
            }
        }
        int result = sumOfSupplies - sumOfBuying;
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(sumOfSupplies).append(System.lineSeparator())
                .append("buy,").append(sumOfBuying).append(System.lineSeparator())
                .append("result,").append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
