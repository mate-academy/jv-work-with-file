package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int outputBuy = 0;
        int outputSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String transaction = bufferedReader.readLine();
            while (transaction != null) {
                String[] trasactionInfoSplitted = transaction.split(",");
                if (trasactionInfoSplitted[ACTION_INDEX].equals("buy")) {
                    outputBuy += Integer.parseInt(trasactionInfoSplitted[QUANTITY_INDEX]);
                } else {
                    outputSupply += Integer.parseInt(trasactionInfoSplitted[QUANTITY_INDEX]);
                }
                transaction = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found" + fromFileName, e);
        }
        String report = createReport(outputBuy, outputSupply);
        sendReportToFile(toFileName, report);
    }

    private String createReport(int outputBuy, int outputSupply) {
        int result = outputSupply - outputBuy;
        return "supply," + outputSupply + System.lineSeparator()
                + "buy," + outputBuy + System.lineSeparator()
                + "result," + result;
    }

    private void sendReportToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}

