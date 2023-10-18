package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_OF_NAME = 0;
    public static final int INDEX_OF_VALUE = 1;
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            buy = 0;
            supply = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                if (split[INDEX_OF_NAME].equals("supply")) {
                    supply += Integer.parseInt(split[INDEX_OF_VALUE]);
                } else if (split[INDEX_OF_NAME].equals("buy")) {
                    buy += Integer.parseInt(split[INDEX_OF_VALUE]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(toFileName)))) {
            String stringBuilder = "supply," + supply + System.lineSeparator() + "buy,"
                    + buy + System.lineSeparator() + "result," + (supply - buy);
            bufferedWriter.write(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
