package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final int AMOUNT = 1;
    private static final int FUNCTION = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String calculated = calculate(data);
        writeToFile(toFileName, calculated);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeToFile(String fileName, String calculate) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(calculate);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }

    private String calculate(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        int amount;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : dataFromFile) {
            String[] lineSeparation = line.split(COMA);
            amount = Integer.parseInt(lineSeparation[AMOUNT]);
            if (lineSeparation[FUNCTION].equals(SUPPLY)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        return stringBuilder.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY).append(",")
                .append(buy).append(System.lineSeparator()).append(RESULT)
                .append(",").append((supply - buy)).toString();
    }
}
