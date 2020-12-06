package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    private static final String STRING_DELIMITER = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFileToList(fromFileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String eachLine : dataFromFile) {
            String[] splitedLine = eachLine.split(STRING_DELIMITER);
            if (splitedLine[0].equals(SUPPLY_OPERATION)) {
                supplyAmount += Integer.valueOf(splitedLine[1]);
            }
            if (splitedLine[0].equals(BUY_OPERATION)) {
                buyAmount += Integer.valueOf(splitedLine[1]);
            }
        }
        String[] report = new String[3];
        report[0] = SUPPLY_OPERATION + STRING_DELIMITER + supplyAmount;
        report[1] = BUY_OPERATION + STRING_DELIMITER + buyAmount;
        report[2] = RESULT_NAME + STRING_DELIMITER + (supplyAmount - buyAmount);
        writeReportToFile(toFileName, report);
    }

    private List<String> readFromFileToList(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            List<String> formFileList = new ArrayList<>();
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                formFileList.add(lineFromFile);
                lineFromFile = bufferedReader.readLine();
            }
            return formFileList;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeReportToFile(String toFileName, String[] report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String reportLine : report) {
                bufferedWriter.write(reportLine);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
