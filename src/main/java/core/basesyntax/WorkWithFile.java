package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String FILE_SEPARATOR = System.lineSeparator();
    private static final String COMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] report = readFromFile(fromFileName);
        writeToFile(toFileName, report);

    }

    private int[] readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        int[] report = {0, 0};
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            int parseInt = 0;
            while (value != null) {
                parseInt = Integer.parseInt(value.split(COMA_SEPARATOR)[1]);
                if (value.startsWith("supply")) {
                    report[0] += parseInt;
                } else {
                    report[1] += parseInt;
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return report;
    }

    private void writeToFile(String toFileName, int[] report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(new StringBuilder().append("supply,").append(report[0])
                    .append(FILE_SEPARATOR).append("buy,").append(report[1])
                    .append(FILE_SEPARATOR).append("result,")
                    .append(report[0] - report[1]).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't writhe to file " + toFileName, e);
        }
    }
}
