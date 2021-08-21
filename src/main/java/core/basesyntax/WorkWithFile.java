package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String COMA = ",";
    private static final String SUPPLY = "supply";
    private static final int NAME_COUNT = 0;
    private static final int VALUE_COUNT = 1;


    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        writeFile(data, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int buyCount = 0;
        int supplyCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.split(COMA)[NAME_COUNT].equals(BUY)) {
                    buyCount += Integer.parseInt(line.split(COMA)[VALUE_COUNT]);
                } else if (line.split(COMA)[NAME_COUNT].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(line.split(COMA)[VALUE_COUNT]);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        stringBuilder.append(SUPPLY).append(COMA).append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buyCount).append(System.lineSeparator())
                .append("result").append(COMA).append(supplyCount - buyCount);
        return stringBuilder.toString();
    }

    private void writeFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(data);
            bufferedWriter.write(System.lineSeparator());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
