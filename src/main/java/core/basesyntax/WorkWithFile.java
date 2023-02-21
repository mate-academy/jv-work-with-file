package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class WorkWithFile {
    private static final int ARRAY_LENGTH = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        out(readFrom(fromFileName), toFileName);
    }

    private int[] readFrom(String fromFileName) {
        int [] result = new int[ARRAY_LENGTH];
        String [] temp;
        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            for (int i = 0; i < strings.size(); i++) {
                temp = strings.get(i).split(",");
                result[0] = (Objects.equals(temp[0], "supply"))
                        ? result[0] + Integer.parseInt(temp[1]) : result[0];
                result[1] = (Objects.equals(temp[0], "buy"))
                        ? result[1] + Integer.parseInt(temp[1]) : result[1];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return result;
    }

    private void out(int [] values, String toFileName) {
        String message = "supply," + values[0] + System.lineSeparator() + "buy," + values[1]
                + System.lineSeparator() + "result," + (values[0] - values [1]);
        File file = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(message);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
        }
    }
}
