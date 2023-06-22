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
    static final int ASSIGNING_ZERO = 0;
    static final int COLUMN1_INDEX = 0;
    static final int COLUMN2_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = ASSIGNING_ZERO;
        int buySum = ASSIGNING_ZERO;
        List<String> fileLines;
        ArrayList<String> columnList;
        try {
            fileLines = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            columnList = new ArrayList<>(Arrays.asList(splitedText));
            if (columnList.get(COLUMN1_INDEX).equals("supply")) {
                supplySum += Integer.parseInt(columnList.get(COLUMN2_INDEX));
            } else {
                buySum += Integer.parseInt(columnList.get(COLUMN2_INDEX));
            }
        }
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            Files.writeString(Path.of(toFileName), "");
            bufferedWriter.write("supply," + supplySum);
            bufferedWriter.write(System.lineSeparator() + "buy," + buySum);
            bufferedWriter.write(System.lineSeparator() + "result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
