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
    static final int NAME_INDEX = 0;
    static final int PARAM_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeWtoFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    public List<String> readFromFile(String fromFileName) {
        List<String> fileLines;
        ArrayList<String> columnList;
        try {
            fileLines = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return fileLines;
    }

    public Integer[] createReport(List<String> fileLines) {
        int supplySum = 0;
        int buySum = 0;
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>(Arrays.asList(splitedText));
            if (columnList.get(NAME_INDEX).equals("supply")) {
                supplySum += Integer.parseInt(columnList.get(PARAM_INDEX));
            } else {
                buySum += Integer.parseInt(columnList.get(PARAM_INDEX));
            }
        }
        return new Integer[]{supplySum, buySum};
    }

    public void writeWtoFile(Integer[] integers, String toFileName) {
        File file = new File(toFileName);
        int supplySum = integers[NAME_INDEX];
        int buySum = integers[PARAM_INDEX];
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            Files.writeString(Path.of(toFileName), "");
            bufferedWriter.write("supply," + supplySum);
            bufferedWriter.write(System.lineSeparator() + "buy," + buySum);
            bufferedWriter.write(System.lineSeparator() + "result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file.getName(), e);
        }
    }
}
