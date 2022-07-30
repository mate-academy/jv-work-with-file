package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int AMMOUNT = 1;
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE = 0;
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] tempArray = value.split(DELIMITER);
                if (tempArray[OPERATION_TYPE].equals(SUPPLY)) {
                    totalSupply += Integer.parseInt(tempArray[AMMOUNT]);
                } else {
                    totalBuy += Integer.parseInt(tempArray[AMMOUNT]);
                }
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName + " ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file" + fromFileName + " ", e);
        }
        String report = createReport(totalSupply, totalBuy, totalSupply - totalBuy);
        writeStatistic(toFileName, report);
    }

    private void writeStatistic(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file " + toFileName + " ", e);
        }
    }

    private String createReport(int totalSupply, int totalBuy, int totalResult) {
        final String buy = "buy";
        final String result = "result";
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(DELIMITER).append(totalSupply)
                .append(System.lineSeparator())
                .append(buy).append(DELIMITER).append(totalBuy).append(System.lineSeparator())
                .append(result).append(DELIMITER).append(totalResult).toString();
    }
}
