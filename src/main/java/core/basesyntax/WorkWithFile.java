package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORD_BUY = "buy";
    private static final String WORD_SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final int NUMBER_ZERO = 0;
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {

        String[] data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private String[] readFile(String fromFileName) {

        File fileToRead = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {

            String info = bufferedReader.readLine();
            StringBuilder contentOfFile = new StringBuilder();

            while (info != null) {
                contentOfFile.append(info).append(SPACE);
                info = bufferedReader.readLine();
            }

            return contentOfFile.toString().split(SPACE);
        } catch (IOException e) {
            throw new RuntimeException("can't read the file: " + fromFileName, e);
        }
    }

    private String createReport(String[] data) {

        int bought = NUMBER_ZERO;
        int supplied = NUMBER_ZERO;

        for (String splittedInfo : data) {
            String[] tempData = splittedInfo.split(COMMA);
            if (tempData[NUMBER_ZERO].startsWith(WORD_BUY)) {
                bought += Integer.parseInt(tempData[1]);
            } else if (tempData[NUMBER_ZERO].startsWith(WORD_SUPPLY)) {
                supplied += Integer.parseInt(tempData[1]);
            }
        }

        int result = supplied - bought;

        StringBuilder infoReport = new StringBuilder();
        infoReport.append(WORD_SUPPLY).append(COMMA).append(supplied)
                .append(System.lineSeparator()).append(WORD_BUY).append(COMMA)
                .append(bought).append(System.lineSeparator())
                .append("result,").append(result);

        return infoReport.toString();
    }

    private void writeToFile(String report, String toFileName) {

        File fileToWrite = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {

            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException("can't write to the file: " + toFileName, e);
        }
    }
}
