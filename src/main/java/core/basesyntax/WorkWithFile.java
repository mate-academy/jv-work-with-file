package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String separator = " ";
    private static final String coma_separator = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String information = getInfoFromFile(fromFileName);
        String report = createReport(information);
        writeToFile(toFileName, report);
    }

    private String getInfoFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder info = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                info.append(value).append(separator);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return info.toString();
    }

    private String createReport(String info) {
        int buy = 0;
        int supply = 0;
        int result;
        String[] array = info.split(separator);
        for (String elment : array) {
            String[] arrayOfElments = elment.split(coma_separator);
            if (arrayOfElments[NAME_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(arrayOfElments[NUMBER_INDEX]);
            } else {
                buy += Integer.parseInt(arrayOfElments[NUMBER_INDEX]);
            }
        }
        result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(coma_separator).append(supply)
                .append(System.lineSeparator()).append(BUY).append(coma_separator)
                .append(buy).append(System.lineSeparator()).append(RESULT)
                .append(coma_separator).append(result);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
