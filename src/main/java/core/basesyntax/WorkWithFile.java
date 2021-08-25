package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = "~!";
    private static final String CSV_FILE_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int FIRST_VALUE = 0;
    private static final int SECOND_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedReader myReader = new BufferedReader(new FileReader(file))) {
            String oneLine;
            while ((oneLine = myReader.readLine()) != null) {
                fileContent.append(oneLine).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("File wasn't read!", e);
        }
        return fileContent.toString();
    }

    private String createReport(String readString) {
        int supply = 0;
        int buy = 0;
        String[] stringsForReport = readString.split(LINE_SEPARATOR);
        for (int i = FIRST_VALUE; i < stringsForReport.length; i++) {
            String[] data = stringsForReport[i].split(CSV_FILE_SEPARATOR);
            if (data[FIRST_VALUE].equals(SUPPLY)) {
                supply += Integer.parseInt(data[SECOND_VALUE]);
            } else if (data[FIRST_VALUE].equals(BUY)) {
                buy += Integer.parseInt(data[SECOND_VALUE]);
            }
        }
        return generateReport(supply, buy);
    }

    private void writeFile(String toFileName, String report) {
        File writeFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile))) {
            bufferedWriter. write(report);
        } catch (IOException e) {
            throw new RuntimeException("File wasn't write!", e);
        }
    }

    private String generateReport(int supply, int buy) {
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
