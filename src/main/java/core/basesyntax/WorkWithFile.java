package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writer(toFileName, calculator(read(fromFileName)));
    }

    public String read(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            String string = bufferedReader.readLine();
            while (string != null) {
                data.append(string).append(" ");
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
        return data.toString();
    }

    public int[] calculator(String str) {
        int[] result = new int[3];
        String[] lines = str.split(" ");
        for (String line : lines) {
            if (line.contains("supply")) {
                result[SUPPLY_INDEX] += Integer.parseInt(line.trim().substring(7));
            } else if (line.contains("buy")) {
                result[BUY_INDEX] += Integer.parseInt(line.trim().substring(4));
            }
        }
        result[2] = result[0] - result[1];
        return result;
    }

    public void writer(String toFileName, int[] result) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            file.createNewFile();
            StringBuilder stringBuilder = new StringBuilder();
            bufferedWriter.write(String.valueOf(stringBuilder.append("supply,")
                    .append(result[SUPPLY_INDEX]).append(System.lineSeparator())
                    .append("buy,").append(result[BUY_INDEX]).append(System.lineSeparator())
                    .append("result,").append(result[RESULT_INDEX])));
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
    }
}
