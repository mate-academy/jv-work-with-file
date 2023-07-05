package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String STRING_SEPARATOR = ",";
    private static final String SHOP_SUPPLIER = "supply,";
    private static final String SHOP_BUY = "buy,";
    private static final String RESULT_COUNT = "result,";
    private static final int ARGUMENT = 0;
    private int sumSupply;
    private int sumBuy;
    private int result;

    protected void getStatistic(String fromFileName, String toFileName) {
        calculateNewFile(fromFileName);
        createReportFile(toFileName);

    }

    private void createFile(String fileName) {

        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("File not created" + fileName, e);
        }
    }

    private void calculateNewFile(String fromFileName) {
        sumSupply = 0;
        sumBuy = 0;
        for (String str : readDataFromFile(fromFileName)) {
            if (getStringSeparator(str).equals("supply")) {
                sumSupply += getSum(str);
            } else {
                sumBuy += getSum(str);
            }

        }
        result = sumSupply - sumBuy;

    }

    private List<String> readDataFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));

        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
    }

    private void createReportFile(String toFileName) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SHOP_SUPPLIER)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(SHOP_BUY)
                .append(sumBuy)
                .append(System.lineSeparator()).append(RESULT_COUNT)
                .append(result)
                .append(System.lineSeparator());
        createFile(toFileName);
        writeNewData(toFileName, reportBuilder.toString());

    }

    private String getStringSeparator(String str) {
        return str.split(STRING_SEPARATOR)[ARGUMENT];

    }

    private void writeNewData(String toFileName, String data) {
        try {
            Files.write(Path.of(toFileName), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Can't write this file", e);
        }
    }

    private int getSum(String str) {
        return Integer.parseInt(str.split(STRING_SEPARATOR)[1]);
    }
}

