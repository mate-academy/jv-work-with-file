package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = ",";
    private static final int BUY_SUM_INDEX = 0;
    private static final int SUPPLY_SUM_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String incomeData = readFromFile(fromFileName);
        String reportString = getReport(incomeData);
        writeToFile(toFileName,reportString);
    }

    private void writeToFile(String toFileName, String reportString) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileToWrite, e);
        }
    }

    private String getReport(String incomeData) {
        int[] resultArray = new int[2];
        String[] temporaryArray = incomeData.split(LINE_SEPARATOR);
        for (int i = 0; i < temporaryArray.length; i += 2) {
            if (temporaryArray[i].trim().equals("supply")) {
                resultArray[SUPPLY_SUM_INDEX] += Integer.parseInt(temporaryArray[i + 1]);
            } else if (temporaryArray[i].trim().equals("buy")) {
                resultArray[BUY_SUM_INDEX] += Integer.parseInt(temporaryArray[i + 1]);
            }
        }
        int reportResult = resultArray[SUPPLY_SUM_INDEX] - resultArray[BUY_SUM_INDEX];
        return "supply" + LINE_SEPARATOR + resultArray[SUPPLY_SUM_INDEX]
                + System.lineSeparator()
                + "buy" + LINE_SEPARATOR + resultArray[BUY_SUM_INDEX]
                + System.lineSeparator()
                + "result" + LINE_SEPARATOR + reportResult;
    }

    private String readFromFile(String fromFileName) {
        File fileToRead = new File(fromFileName);
        try {
            List<String> stringList = Files.readAllLines(fileToRead.toPath());
            return String.join(",",stringList);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileToRead, e);
        }
    }
}
