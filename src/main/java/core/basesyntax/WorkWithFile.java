package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String WHITE_SPACE_REGEX = " ";
    private static final String CSV_SEPARATOR = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        createFileWithGroupedData(toFileName, createReport(extractDataFromFile(fromFileName)));
    }

    private String extractDataFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String dataFromFile = reader.readLine();
            while (dataFromFile != null) {
                builder.append(dataFromFile).append(WHITE_SPACE_REGEX);
                dataFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file " + fileName);
        }
        return builder.toString();
    }

    private String[] createReport(String info) {
        String[] values = info.split(WHITE_SPACE_REGEX);
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (String value : values) {
            String[] splitValue = value.split(CSV_SEPARATOR);
            if (splitValue[INDEX_ZERO].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(splitValue[1]);
            }
            if (splitValue[INDEX_ZERO].equals(BUY)) {
                sumOfBuy += Integer.parseInt(splitValue[1]);
            }
        }
        String[] groupedData = new String[3];
        groupedData[INDEX_ZERO] = SUPPLY + CSV_SEPARATOR + sumOfSupply;
        groupedData[INDEX_ONE] = BUY + CSV_SEPARATOR + sumOfBuy;
        groupedData[INDEX_TWO] = RESULT + CSV_SEPARATOR + (sumOfSupply - sumOfBuy);
        return groupedData;
    }

    private void createFileWithGroupedData(String nameOfReportFile, String[] data) {
        File file = new File(nameOfReportFile);
        for (String value : data) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(value);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can not write data to file", e);
            }
        }
    }
}
