package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile report = new WorkWithFile();
        report.writeReportToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fileName) {
        File fileRead = new File(fileName);
        StringBuilder result = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(fileRead))) {
            String bufferValue = bf.readLine();
            while (bufferValue != null) {
                result.append(bufferValue).append(System.lineSeparator());
                bufferValue = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fileName, e);
        }
        return result.toString();
    }

    private String[] createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        for (String string: dataFromFile.split("\\s+")) {
            if (string.contains(SUPPLY)) {
                supply += Integer.parseInt(string.split(COMMA_SEPARATOR)[1]);
            } else {
                buy += Integer.parseInt(string.split(COMMA_SEPARATOR)[1]);
            }
        }
        StringBuilder supplyBuilder = new StringBuilder().append(SUPPLY)
                .append(COMMA_SEPARATOR).append(supply);
        StringBuilder buyBuilder = new StringBuilder().append(BUY)
                .append(COMMA_SEPARATOR).append(buy);
        StringBuilder resultBuilder = new StringBuilder().append(RESULT)
                .append(COMMA_SEPARATOR).append(supply - buy);
        return new String[]{supplyBuilder.toString(), buyBuilder.toString(),
                resultBuilder.toString()};
    }

    private void writeReportToFile(String[] report, String toFile) {
        File fileWrite = new File(toFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileWrite))) {
            for (int i = 0; i < report.length; i++) {
                bw.write(report[i] + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }
}
