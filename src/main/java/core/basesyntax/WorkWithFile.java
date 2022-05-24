package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_NAME = 0;
    private static final int INDEX_OF_NUM = 1;
    private static final String VARIABLE_BUY = "buy";
    private static final String VARIABLE_SUPPLY = "supply";
    private static final String VARIABLE_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    private void writeToFile(String fromFileName, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String allData = getReport(readInfoFromFile(fromFileName));
            writer.write(allData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }

    private String readInfoFromFile(String fromFileName) {
        StringBuilder allInfoFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            while (data != null) {
                allInfoFromFile.append(data).append(" ");
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return allInfoFromFile.toString();
    }

    private String getReport(String allData) {
        StringBuilder reportInfo = new StringBuilder();
        String [] arr = allData.split(" ");
        int sumBuy = 0;
        int sumSupply = 0;
        for (String ar : arr) {
            String [] info = ar.split(",");
            if (info[INDEX_OF_NAME].equals(VARIABLE_BUY)) {
                sumBuy += Integer.parseInt(info[INDEX_OF_NUM]);
            } else {
                sumSupply += Integer.parseInt(info[INDEX_OF_NUM]);
            }
        }
        int result = sumSupply - sumBuy;
        reportInfo.append(VARIABLE_SUPPLY).append(",").append(sumSupply)
                .append(System.lineSeparator()).append(VARIABLE_BUY).append(",")
                .append(sumBuy).append(System.lineSeparator()).append(VARIABLE_RESULT)
                .append(",").append(result);
        return reportInfo.toString();
    }
}
