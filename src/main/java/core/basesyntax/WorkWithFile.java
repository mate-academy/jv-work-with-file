package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int PARAM_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileLines = readFromFile(fromFileName);
        String csvReport = createReport(fileLines);
        writeWtoFile(csvReport, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private String createReport(List<String> fileLines) {
        int supplySum = 0;
        int buySum = 0;
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            if (splitedText[NAME_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(splitedText[PARAM_INDEX]);
            } else {
                buySum += Integer.parseInt(splitedText[PARAM_INDEX]);
            }
        }
        return "supply," + supplySum + "_"
                + "buy," + buySum + "_"
                + "result," + (supplySum - buySum);
    }

    private void writeWtoFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            Files.writeString(Path.of(toFileName), "");
            System.out.println(file.length());
            String[] splittedReport = report.split("_");
            for (int i = 0; i < splittedReport.length; i++) {
                if (i == 0) {
                    bufferedWriter.write(splittedReport[i]);
                } else {
                    bufferedWriter.write(System.lineSeparator() + splittedReport[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file.getName(), e);
        }
    }
}
