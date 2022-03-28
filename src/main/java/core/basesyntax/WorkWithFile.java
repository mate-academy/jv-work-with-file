package core.basesyntax;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int CHECK_POINT = 0;
    private static final int COUNT_POINT = 1;
    private static final String SUPPLY_LYNE = "supply,";
    private static final String BUY_LYNE = "buy,";
    private static final String RESULT_LYNE = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(fromFileName));
            String[] nextLine;
            while (true) {
                try {
                    if ((nextLine = csvReader.readNext()) == null) {
                        break;
                    }
                } catch (IOException | CsvValidationException e) {
                    throw new RuntimeException(e);
                }
                if (nextLine[CHECK_POINT].equals("supply")) {
                    supplyCount += Integer.parseInt(nextLine[COUNT_POINT]);
                } else {
                    buyCount += Integer.parseInt(nextLine[COUNT_POINT]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_LYNE)
                .append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY_LYNE)
                .append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT_LYNE)
                .append(supplyCount - buyCount);
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
