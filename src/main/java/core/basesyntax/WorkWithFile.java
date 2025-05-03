package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String DEMILITER = ",";
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final short FIRST_COLUMN = 0;
    private static final short SECOND_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFile(fromFileName)));
    }

    private List<String> readFile(String fromFileName) {
        try {
            File file = new File(fromFileName);
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private String createReport(List<String> listData) {
        String[] supplyBuy;
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : listData) {
            supplyBuy = line.split(DEMILITER);
            if (SUPPLY_WORD.equals(supplyBuy[FIRST_COLUMN])) {
                sumSupply += Integer.parseInt(supplyBuy[SECOND_COLUMN]);
            }
            if (BUY_WORD.equals(supplyBuy[FIRST_COLUMN])) {
                sumBuy += Integer.parseInt(supplyBuy[SECOND_COLUMN]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(sumSupply).append(System.lineSeparator());
        result.append("buy,").append(sumBuy).append(System.lineSeparator());
        result.append("result,").append(sumSupply - sumBuy).append(System.lineSeparator());
        return result.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: " + toFileName, e);
        }
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
