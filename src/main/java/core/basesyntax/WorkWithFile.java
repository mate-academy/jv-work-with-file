package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String BUY_ITEM = "buy";
    private static final String SUPPLY_ITEM = "supply";
    private static final String RESULT_ITEM = "result";
    private static final String CSV_DIVIDER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(generateReport(readFile(fromFileName)), toFileName);
    }

    private List<String> readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            List<String> listToReturn = new ArrayList<>();
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                listToReturn.add(row);
            }
            return listToReturn;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from input file " + fromFileName, e);
        }
    }

    private String generateReport(List<String> dataFromFile) {
        int buy = 0;
        int supply = 0;
        for (String str : dataFromFile) {
            String[] data = str.split(CSV_DIVIDER);
            if (data[0].equals(BUY_ITEM)) {
                buy += Integer.parseInt(data[1]);
            } else {
                supply += Integer.parseInt(data[1]);
            }
        }
        StringBuilder strWriteToFile = new StringBuilder();
        strWriteToFile.append(SUPPLY_ITEM).append(CSV_DIVIDER).append(supply)
                .append(System.lineSeparator())
                .append(BUY_ITEM).append(CSV_DIVIDER).append(buy)
                .append(System.lineSeparator()).append(RESULT_ITEM).append(CSV_DIVIDER)
                .append(supply - buy);
        return strWriteToFile.toString();
    }

    private void writeToFile(String dataToWrite, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
