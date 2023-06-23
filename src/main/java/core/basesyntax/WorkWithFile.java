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
        List<String> fileLines = readFromFile(fromFileName);
        List<Integer> csvReport = createReport(fileLines);
        writeWtoFile(csvReport, toFileName);
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

    public List<Integer> createReport(List<String> fileLines) {
        List<Integer> result = new ArrayList<>();
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
        result.add(supplySum);
        result.add(buySum);
        result.add(result.get(0) - result.get(1));
        return result;
    }

    public void writeWtoFile(List<Integer> report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            Files.writeString(Path.of(toFileName), "");
            bufferedWriter.write("supply," + report.get(NAME_INDEX));
            bufferedWriter.write(System.lineSeparator() + "buy," + report.get(PARAM_INDEX));
            bufferedWriter.write(System.lineSeparator() + "result," + report.get(RESULT_INDEX));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file.getName(), e);
        }
    }
}
