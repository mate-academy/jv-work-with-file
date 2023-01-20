package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY = 0;
    private static final int INDEX_OF_BUY = 1;
    private static final int INDEX_OF_RESULT = 2;
    private static final int SIZE_OF_REPORT = 3;
    private static final String SPLIT_BY = ",";
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private String line = "";

    private ArrayList<String> readFile(String fromFileName) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader csvBufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = csvBufferedReader.readLine()) != null) {
                lines.add(line);
            }
            csvBufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return lines;
    }

    private int[] generateReport(ArrayList<String> lines) {
        int[] report = new int[SIZE_OF_REPORT];
        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(SPLIT_BY);
            if (data[0].equals(SUPPLY_STRING)) {
                report[INDEX_OF_SUPPLY] += Integer.parseInt(data[1]);
            } else if (data[0].equals(BUY_STRING)) {
                report[INDEX_OF_BUY] += Integer.parseInt(data[1]);
            }
        }
        report[INDEX_OF_RESULT] = report[INDEX_OF_SUPPLY] - report[INDEX_OF_BUY];
        return report;
    }

    private void writeToFile(int[] report, String toFileName) {
        try {
            StringBuilder reportStringBuilder = new StringBuilder();
            reportStringBuilder.append(SUPPLY_STRING).append(SPLIT_BY)
                    .append(report[INDEX_OF_SUPPLY])
                    .append(System.lineSeparator());
            reportStringBuilder.append(BUY_STRING).append(SPLIT_BY)
                    .append(report[INDEX_OF_BUY])
                    .append(System.lineSeparator());
            reportStringBuilder.append(RESULT_STRING).append(SPLIT_BY)
                    .append(report[INDEX_OF_RESULT]);
            FileWriter fileWriter = new FileWriter(new File(toFileName));
            fileWriter.write(reportStringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> lines = readFile(fromFileName);
        int[] report = generateReport(lines);
        writeToFile(report, toFileName);
    }
}
