package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int VALUE_POSITION = 1;
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final String BUY_WORD = "buy";
    private static final String CSV_DELIMITER = ",";
    private static final String RESULT_WORD = "result";
    private static final String SUPPLY_WORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File(fromFileName)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(CSV_DELIMITER);
                if (data[OPERATION_TYPE_POSITION].equals(SUPPLY_WORD)) {
                    totalSupply += Integer.parseInt(data[VALUE_POSITION]);
                }
                if (data[OPERATION_TYPE_POSITION].equals(BUY_WORD)) {
                    totalBuy += Integer.parseInt(data[VALUE_POSITION]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName, e);
        }
        File outputFile = new File(toFileName);
        try {
            Files.write(outputFile.toPath(), makeReport(totalSupply, totalBuy).getBytes(),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write in file " + toFileName, e);
        }
    }

    private String makeReport(int totalSupply, int totalBuy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_WORD + CSV_DELIMITER).append(totalSupply)
                     .append(System.lineSeparator());
        stringBuilder.append(BUY_WORD + CSV_DELIMITER).append(totalBuy)
                     .append(System.lineSeparator());
        stringBuilder.append(RESULT_WORD + CSV_DELIMITER).append(totalSupply - totalBuy)
                     .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
