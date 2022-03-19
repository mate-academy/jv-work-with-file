package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        File importFile = new File(fromFileName);
        File reportFile = new File(toFileName);
        StringBuilder importData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(importFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                importData.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        String[] splitData = importData.toString().split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String element : splitData) {
            String[] line = element.split(COMMA);
            if (line[0].equals(SUPPLY)) {
                supply += Integer.parseInt(line[1]);
            } else if (line[0].equals(BUY)) {
                buy += Integer.parseInt(line[1]);
            }
        }
        int result = supply - buy;
        StringBuilder reportData = new StringBuilder();
        reportData.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            bufferedWriter.write(reportData.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
