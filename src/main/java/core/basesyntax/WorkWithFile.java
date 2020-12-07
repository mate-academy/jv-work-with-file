package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final char COMMA = ',';
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String informationFromFile = readFile(fromFileName);
        writeFile(toFileName,
                writeReport(calculateSupply(informationFromFile),
                        calculateBuy(informationFromFile)));
    }

    private String readFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(COMMA);
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file.", e);
        }
    }

    private int calculateSupply(String value) {
        int supply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] data = value.split(String.valueOf(COMMA));
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(SUPPLY)) {
                supply += Integer.parseInt(data[i + 1]);
            }
        }
        return supply;
    }

    private int calculateBuy(String value) {
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] data = value.split(String.valueOf(COMMA));
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(BUY)) {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        return buy;
    }

    private String writeReport(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator()).append(BUY)
                .append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(supply - buy).toString();
    }

    private void writeFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data file", e);
        }
    }
}
