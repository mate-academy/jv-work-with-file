package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] valueArray = line.split(",");
                String operation = valueArray[OPERATION_INDEX];
                int amount = Integer.parseInt(valueArray[AMOUNT_INDEX]);
                if (operation.equals("supply")) {
                    totalSupply += amount;
                } else if (operation.equals("buy")) {
                    totalBuy += amount;
                }
                line = bufferedReader.readLine();
            }
            writeReportToFile(totalSupply, totalBuy, toFileName);
        } catch (Exception e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }

    private String createReport(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;
        return "supply" + "," + totalSupply + System.lineSeparator() + "buy"
                + "," + totalBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeReportToFile(int totalSupply, int totalBuy, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport(totalSupply, totalBuy));
        } catch (Exception e) {
            throw new RuntimeException("Can't write a file", e);
        }
    }
}
