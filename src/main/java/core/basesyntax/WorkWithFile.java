package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int INDEX_OF_VALUE = 1;
    private static final int INDEX_OF_WORD = 0;
    private static final String BUY_WORD = "buy";
    private static final String CSV_DELIMITER = ",";
    private static final String RESULT_WORD = "result";
    private static final String SUPPLY_WORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File(fromFileName)))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] temp = value.split(CSV_DELIMITER);
                if (temp[INDEX_OF_WORD].equals(SUPPLY_WORD)) {
                    totalSupply += Integer.parseInt(temp[INDEX_OF_VALUE]);
                }
                if (temp[INDEX_OF_WORD].equals(BUY_WORD)) {
                    totalBuy += Integer.parseInt(temp[INDEX_OF_VALUE]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SUPPLY_WORD + CSV_DELIMITER)
                     .append(totalSupply)
                     .append(System.lineSeparator());
        stringBuilder.append(BUY_WORD + CSV_DELIMITER)
                     .append(totalBuy)
                     .append(System.lineSeparator());
        stringBuilder.append(RESULT_WORD + CSV_DELIMITER)
                     .append(totalSupply - totalBuy)
                     .append(System.lineSeparator());

        File outputFile = new File(toFileName);
        try {
            Files.write(outputFile.toPath(), stringBuilder.toString().getBytes(),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write in file", e);
        }
    }
}
