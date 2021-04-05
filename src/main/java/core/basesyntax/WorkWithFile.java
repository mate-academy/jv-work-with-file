package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String LINE_SEPARATOR = " ";
    public static final String SUPPLY = "supply";
    public static final String VALUE_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        String [] dataArray = dataFromFile.split(LINE_SEPARATOR);
        for (String dataLine : dataArray) {
            if (dataLine.contains(SUPPLY)) {
                totalSupply += Integer.parseInt(dataLine.substring(dataLine.indexOf(",") + 1));
            } else if (dataLine.contains(BUY)) {
                totalBuy += Integer.parseInt(dataLine.substring(dataLine.indexOf(",") + 1));
            }
        }
        return new StringBuilder().append(SUPPLY).append(VALUE_SEPARATOR).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY).append(VALUE_SEPARATOR).append(totalBuy)
                .append(System.lineSeparator())
                .append("result").append(VALUE_SEPARATOR).append(totalSupply - totalBuy)
                .toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write information", e);
        }
    }
}
