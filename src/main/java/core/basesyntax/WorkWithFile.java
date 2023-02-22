package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class WorkWithFile {
    public static final int ARRAY_LENGTH = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> strings = null;
        try {
            strings = Files.readAllLines(file.toPath());

        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return strings;
    }

    private String createReport(List<String> stringList) {
        int [] result = new int[ARRAY_LENGTH];
        String [] temp;
        for (int i = 0; i < stringList.size(); i++) {
            temp = stringList.get(i).split(",");
            result[0] = (Objects.equals(temp[0], "supply"))
                    ? result[0] + Integer.parseInt(temp[1]) : result[0];
            result[1] = (Objects.equals(temp[0], "buy"))
                    ? result[1] + Integer.parseInt(temp[1]) : result[1];
        }
        return "supply," + result[0] + System.lineSeparator() + "buy," + result[1]
                + System.lineSeparator() + "result," + (result[0] - result[1]);
    }

    private void writeToFile(String message, String toFileName) {
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
