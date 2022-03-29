package core.basesyntax;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_POINT = 0;
    private static final int SECOND_POINT = 1;
    private static final String SUPPLY_LINE = "supply";
    private static final String BUY_LINE = "buy";
    private static final String RESULT_LINE = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String value = getDataFromFile(fromFileName);
        String sentence = dataCalculation(value);
        writeToFile(sentence, toFileName);
    }

    private String getDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (CSVReader csvReader = new CSVReader(new FileReader(fromFileName))) {
            String[] nextLine;
            while (true) {
                try {
                    if ((nextLine = csvReader.readNext()) == null) {
                        break;
                    }
                } catch (IOException | CsvValidationException e) {
                    throw new RuntimeException(e);
                }
                stringBuilder.append(nextLine[FIRST_POINT])
                        .append(",")
                        .append(nextLine[SECOND_POINT])
                        .append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return stringBuilder.toString().trim();
    }

    private String dataCalculation(String value) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder calculatorResult = new StringBuilder();
        String[] array = value.split(" ");
        for (String s : array) {
            String[] split = s.split(",");
            if (split[FIRST_POINT].equals(SUPPLY_LINE)) {
                supplyCount += Integer.parseInt(split[SECOND_POINT]);
            } else {
                buyCount += Integer.parseInt(split[SECOND_POINT]);
            }
        }
        calculatorResult.append(SUPPLY_LINE + COMA)
                .append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY_LINE + COMA)
                .append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT_LINE + COMA)
                .append(supplyCount - buyCount);
        return calculatorResult.toString();
    }

    private void writeToFile(String statistic, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
