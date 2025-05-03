package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeInFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value);
                stringBuilder.append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String rows) {
        String[] split = rows.split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        int supplyInt = 0;
        int buyInt = 0;
        String buyConstant = "buy";
        String supplyConstant = "supply";
        for (String strings: split) {
            String[]record = strings.split(",");
            String count = record[1];
            if (record[0].equals(supplyConstant)) {
                supplyInt += Integer.parseInt(count);
            }
            if (record[0].equals(buyConstant)) {
                buyInt += Integer.parseInt(count);
            }
        }
        String resultTotal = "result";
        stringBuilder.append(supplyConstant).append(",")
                .append(supplyInt).append(System.lineSeparator());
        stringBuilder.append(buyConstant).append(",")
                .append(buyInt).append(System.lineSeparator());
        stringBuilder.append(resultTotal).append(",")
                .append((supplyInt - buyInt)).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeInFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file " + toFileName,e);
        }
    }
}
