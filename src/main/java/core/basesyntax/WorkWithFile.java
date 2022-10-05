package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "\\W+";
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SPLIT_STRING = " ";
    private static final String SYSTEM_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String fileRead = readFromFile(fromFileName);
        String[] report = getInfoForReport(fileRead);
        writeToFile(report, toFileName);

    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SYSTEM_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private String[] getInfoForReport(String fileRead) {
        String[] separateFile = fileRead.split(REGEX);
        int supply = 0;
        int buy = 0;
        int result;
        for (int i = 0; i < separateFile.length; i += 2) {
            if (separateFile[i].equals(SUPPLY)) {
                supply += Integer.parseInt(separateFile[i + 1]);
            }
        }
        for (int i = 0; i < separateFile.length; i += 2) {
            if (separateFile[i].equals(BUY)) {
                buy += Integer.parseInt(separateFile[i + 1]);
            }
        }
        result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supply).append(SYSTEM_SEPARATOR)
                .append(BUY).append(COMMA).append(buy).append(SYSTEM_SEPARATOR)
                .append(RESULT).append(COMMA).append(result);
        return report.toString().split(SPLIT_STRING);
    }

    private void writeToFile(String[] getInfoForReport, String toFileName) {
        File file = new File(toFileName);
        for (String info : getInfoForReport) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(info + SYSTEM_SEPARATOR);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
