package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] report;
        try {
            report = createReport(fromFileName);
            writeToFile(toFileName, report[0], report[1], report[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read/write file.", e);
        }
    }

    private int[] createReport(final String fromFileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            //report[0] = supply; report[1] = buy; report[2] = result = supply - buy;
            int[] report = new int[3];
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] words = line.split(",");
                if (SUPPLY.equals(words[0])) {
                    report[0] += Integer.parseInt(words[1]);
                } else if (BUY.equals(words[0])) {
                    report[1] += Integer.parseInt(words[1]);
                }
            }
            report[2] = report[0] - report[1];
            return report;
        }
    }

    private void writeToFile(final String toFileName, final int supply, final int buy,
                             final int result) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator());
            stringBuilder.append(BUY).append(",").append(buy).append(System.lineSeparator());
            stringBuilder.append(RESULT).append(",").append(result).append(System.lineSeparator());
            bufferedWriter.write(stringBuilder.toString());
        }
    }
}
