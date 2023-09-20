package core.basesyntax;

import core.basesyntax.suppliers.AmountSupplier;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class WorkWithFile {
    private AmountSupplier amountSupplier = new AmountSupplier();

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        ArrayList<String[]> data = getDataFromFile(new File(fromFileName));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(getStringWithStatistic(data));
        } catch (IOException ioException) {
            throw new RuntimeException("Cannot write data to file - " + file, ioException);
        }
    }

    private String getStringWithStatistic(ArrayList<String[]> data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(amountSupplier.getTotalSupplyAmount(data))
                .append(System.lineSeparator())
                .append("buy,").append(amountSupplier.getTotalBuyAmount(data))
                .append(System.lineSeparator())
                .append("result,").append(amountSupplier.getResultAmount(data));
        return stringBuilder.toString();
    }

    private ArrayList<String[]> getDataFromFile(File file) {
        ArrayList<String[]> data = new ArrayList<>();
        try {
            for (String datum : Files.readAllLines(file.toPath())) {
                data.add(datum.split(","));
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Cannot read data from file - " + file + ioException);
        }
        return data;
    }
}
