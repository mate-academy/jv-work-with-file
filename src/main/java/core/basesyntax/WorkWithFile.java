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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply,"
                    + amountSupplier.getTotalSupplyAmount(getDataFromFile(new File(fromFileName)))
                    + System.lineSeparator());
            bufferedWriter.write("buy,"
                    + amountSupplier.getTotalBuyAmount(getDataFromFile(new File(fromFileName)))
                    + System.lineSeparator());
            bufferedWriter.write("result,"
                    + amountSupplier.getResultAmount(getDataFromFile(new File(fromFileName))));
        } catch (IOException ioException) {
            throw new RuntimeException("Cannot write data to file - " + file, ioException);
        }
    }

    public ArrayList<String[]> getDataFromFile(File file) {
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
