package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        TransactionProcessor transactionProcessor = new TransactionProcessor();
        ReportBuilder reportBuilder = new ReportBuilder();

        ReportWriter reportWriter = new ReportWriter();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fromFileName))) {
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                transactionProcessor.process(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file: " + fromFileName, e);
        }

        int supply = transactionProcessor.getTotalSupply();
        int buy = transactionProcessor.getTotalBuy();
        int result = transactionProcessor.getResult();

        String report = reportBuilder.buildReport(supply, buy, result);
        reportWriter.writeReport(toFileName, report);
    }
}
