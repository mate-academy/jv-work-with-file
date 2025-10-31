package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";
    private int buy;
    private int supply;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] value = readAndOperation(fromFileName);
        writeReport(toFileName, value[0], value[1], value[2]);
    }

    private int[] readAndOperation(String fromFileName) {
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String lines;
            String[] value;
            int buy = 0;
            int supply = 0;
            while ((lines = reader.readLine()) != null) {
                value = lines.split(",");
                if (value.length < 2) {
                    continue;
                }
                if (value[0].equals(OPERATION_BUY)) {
                    buy += Integer.parseInt(value[1]);
                } else if (value[0].equals(OPERATION_SUPPLY)) {
                    supply += Integer.parseInt(value[1]);
                }
            }
            int result = supply - buy;
            return new int[]{supply, buy, result};
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
    }

    private void writeReport(String toFile, int supply, int buy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file " + toFile, e);
        }
    }
}
