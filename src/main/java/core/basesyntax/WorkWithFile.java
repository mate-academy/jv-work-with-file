package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int ACTION = 0;
    private static final int QUANTITY = 1;
    private static final String BUY_ACTION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int outputBuy = 0;
        int outputSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String transaction = bufferedReader.readLine();
            while (transaction != null) {
                String[] trasactionInfoSplitted = transaction.split(",");
                if (trasactionInfoSplitted[ACTION].equals(BUY_ACTION)) {
                    outputBuy += Integer.parseInt(trasactionInfoSplitted[QUANTITY]);
                } else {
                    outputSupply += Integer.parseInt(trasactionInfoSplitted[QUANTITY]);
                }
                transaction = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found" + fromFileName, e);
        }
        reportCreator(outputBuy, outputSupply, toFileName);
    }

    private void reportCreator(int outputBuy, int outputSupply, String toFileName) {
        int result = outputSupply - outputBuy;
        String report = "supply," + outputSupply + System.lineSeparator()
                + "buy," + outputBuy + System.lineSeparator()
                + "result," + result;
        sendReportToFile(toFileName, report);
    }

    private void sendReportToFile(String toFileName, String report) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}

