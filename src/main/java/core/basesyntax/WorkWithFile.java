package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int POSITION_NAME = 0;
    private static final int POSITION_SUM = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = read(fromFileName);
        write(toFileName, calculateReport(data));
    }

    private String read(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found" + file.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file.getName(), e);
        }
        return builder.toString();
    }

    private String calculateReport(String data) {
        String[] arrayLines = data.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : arrayLines) {
            String[] dataParts = line.split(COMMA);
            if (dataParts[POSITION_NAME].equals(SUPPLY)) {
                supplySum += Integer.parseInt(dataParts[POSITION_SUM]);
            }
            if (dataParts[POSITION_NAME].equals(BUY)) {
                buySum += Integer.parseInt(dataParts[POSITION_SUM]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplySum - buySum);
        return builder.toString();
    }

    private void write(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file" + file.getName(), e);
        }
    }
}
