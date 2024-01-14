package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String RESULT_FIELD = "result";
    private static final String LINES_SEPARATOR = " ";
    private static final String WORDS_SEPARATOR = ",";
    private static final int DATA_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private final String separator = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = countData(readFromFile(fromFileName));
        writeToFile(report, toFileName);
    }

    private StringBuilder readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(LINES_SEPARATOR);
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        } catch (IOException e1) {
            throw new RuntimeException("Can't read file " + fileName, e1);
        }
        return builder;
    }

    private StringBuilder countData(StringBuilder data) {
        String[] dataArray = data.toString().split(LINES_SEPARATOR);
        int sumSupply = 0;
        int sumBuy = 0;
        for (String dataLine : dataArray) {
            String[] dataLineArray = dataLine.split(WORDS_SEPARATOR);
            if (dataLineArray[DATA_INDEX].equals(SUPPLY_FIELD)) {
                sumSupply += parseInt(dataLineArray[VALUE_INDEX]);
            }
            if (dataLineArray[DATA_INDEX].equals(BUY_FIELD)) {
                sumBuy += parseInt(dataLineArray[VALUE_INDEX]);
            }
        }
        int result = sumSupply - sumBuy;
        return writeReport(sumSupply, sumBuy, result);
    }

    private StringBuilder writeReport(int sumSupply, int sumBuy, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_FIELD)
                .append(WORDS_SEPARATOR)
                .append(sumSupply)
                .append(separator)
                .append(BUY_FIELD)
                .append(WORDS_SEPARATOR)
                .append(sumBuy)
                .append(separator)
                .append(RESULT_FIELD)
                .append(WORDS_SEPARATOR)
                .append(result);
        return builder;
    }

    private void writeToFile(StringBuilder report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(report));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
