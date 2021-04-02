package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String DATA_SEPARATOR = " ";
    public static final String VALUES_SEPARATOR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(createReport(readFile(fromFileName)), toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(DATA_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        String[] dataArray = dataFromFile.split(DATA_SEPARATOR);
        int supplySum = 0;
        int buySum = 0;

        for (String s : dataArray) {
            String[] values = s.split(VALUES_SEPARATOR);
            if (values[0].equals(SUPPLY)) {
                supplySum += Integer.parseInt(values[1]);
            } else {
                buySum += Integer.parseInt(values[1]);
            }
        }
        return new StringBuilder().append(SUPPLY).append(VALUES_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(VALUES_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(VALUES_SEPARATOR).append(supplySum - buySum)
                .toString();
    }

    private void writeFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
