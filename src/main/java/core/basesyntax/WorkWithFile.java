package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";
    private static final String RESULT_TEXT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String[] calculated = calculate(data);
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

    private void writeToFile(String fileName, String[] calculate) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (String data : calculate) {
                bufferedWriter.write(data);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }

    private String[] calculate(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        int amount;
        for (String line : dataFromFile) {
            String[] lineSeparation = line.split(",");
            amount = Integer.parseInt(lineSeparation[1]);
            if (lineSeparation[0].equals(SUPLY_TEXT)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        return new String[]{SUPLY_TEXT + ","
                + supply, BUY_TEXT + ","
                + buy, RESULT_TEXT + "," + (supply - buy)};
    }
}
