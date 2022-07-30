package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName, toFileName);
        writeReportToFile(report, toFileName);
    }

    private String createReport(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitedRow = line.split(",");
                String operation = splitedRow[OPERATION_INDEX];
                int amount = Integer.parseInt(splitedRow[AMOUNT_INDEX]);
                if (operation.equals("supply")) {
                    totalSupply += amount;
                } else if (operation.equals("buy")) {
                    totalBuy += amount;
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read a file" + toFileName, e);
        }
        int result = totalSupply - totalBuy;
        return "supply" + "," + totalSupply + System.lineSeparator() + "buy"
                + "," + totalBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write a file" + toFileName, e);
        }
    }
}
