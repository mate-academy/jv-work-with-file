package core.basesyntax;

import core.basesyntax.suppliers.AmountSupplier;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private AmountSupplier amountSupplier = new AmountSupplier();

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String[]> data = readFromFile(new File(fromFileName));
        String report = generateReport(data);
        writeToFile(new File(toFileName), report);
    }

    private ArrayList<String[]> readFromFile(File file) {
        ArrayList<String[]> data = new ArrayList<>();
        try {
            for (String datum : Files.readAllLines(file.toPath())) {
                data.add(datum.split(SEPARATOR));
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Cannot read data from file - " + file + ioException);
        }
        return data;
    }

    private String generateReport(ArrayList<String[]> data) {
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(amountSupplier.getTotalSupplyAmount(data))
                .append(System.lineSeparator())
                .append("buy,").append(amountSupplier.getTotalBuyAmount(data))
                .append(System.lineSeparator())
                .append("result,").append(amountSupplier.getResultAmount(data));
        return report.toString();
    }

    private void writeToFile(File file, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException ioException) {
            throw new RuntimeException("Cannot write data to file - " + file, ioException);
        }
    }
}
