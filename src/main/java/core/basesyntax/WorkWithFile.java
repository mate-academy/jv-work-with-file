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
    public static final int SORTING_INDEX = 0;
    public static final int VALUE_INDEX = 1;

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
            result[SORTING_INDEX] = (Objects.equals(temp[SORTING_INDEX], "supply"))
                    ? result[SORTING_INDEX]
                    + Integer.parseInt(temp[VALUE_INDEX]) : result[SORTING_INDEX];
            result[VALUE_INDEX] = (Objects.equals(temp[SORTING_INDEX], "buy"))
                    ? result[VALUE_INDEX]
                    + Integer.parseInt(temp[VALUE_INDEX]) : result[VALUE_INDEX];
        }
        return "supply," + result[SORTING_INDEX] + System.lineSeparator() + "buy,"
                + result[VALUE_INDEX] + System.lineSeparator()
                + "result," + (result[SORTING_INDEX] - result[VALUE_INDEX]);
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
