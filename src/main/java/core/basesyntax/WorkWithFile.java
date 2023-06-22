package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int PARAM_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeWtoFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    public List<String> readFromFile(String fromFileName) {
        List<String> fileLines;
        try {
            fileLines = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return fileLines;
    }

    public int[] createReport(List<String> fileLines) {
        int[] result = new int[3];
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>(Arrays.asList(splitedText));
            if (columnList.get(NAME_INDEX).equals("supply")) {
                result[0] += Integer.parseInt(columnList.get(PARAM_INDEX));
            } else {
                result[1] += Integer.parseInt(columnList.get(PARAM_INDEX));
            }
        }
        result[2] = result[0] - result[1];
        return result;
    }

    public void writeWtoFile(int[] report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            Files.writeString(Path.of(toFileName), "");
            bufferedWriter.write("supply," + report[NAME_INDEX]);
            bufferedWriter.write(System.lineSeparator() + "buy," + report[PARAM_INDEX]);
            bufferedWriter.write(System.lineSeparator() + "result," + report[RESULT_INDEX]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file.getName(), e);
        }
    }
}
