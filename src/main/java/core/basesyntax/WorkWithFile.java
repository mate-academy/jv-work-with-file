package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NUMBER_OF_REPORT_FIELD = 3;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final String BUY_FIELD = "buy";
    private static final String SUPPLY_FIELD = "supply";
    private static final String RESULT_FIELD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] reportArray = new int[NUMBER_OF_REPORT_FIELD];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsArray = line.split(",");
                for (int i = 0; i < wordsArray.length; i++) {
                    if (wordsArray[i].equals(SUPPLY_FIELD)) {
                        i++;
                        reportArray[SUPPLY_INDEX] += Integer.parseInt(wordsArray[i]);
                        break;
                    }
                    if (wordsArray[i].equals(BUY_FIELD)) {
                        i++;
                        reportArray[BUY_INDEX] += Integer.parseInt(wordsArray[i]);
                        break;
                    }
                }
            }
            reportArray[RESULT_INDEX] = reportArray[SUPPLY_INDEX] - reportArray[BUY_INDEX];
            writeToFile(reportArray, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private void writeToFile(int[] report, String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < report.length; i++) {
                if (i == SUPPLY_INDEX) {
                    writeField(bufferedWriter, SUPPLY_FIELD, report[i], true);
                }
                if (i == BUY_INDEX) {
                    writeField(bufferedWriter, BUY_FIELD, report[i], true);
                }
                if (i == RESULT_INDEX) {
                    writeField(bufferedWriter, RESULT_FIELD, report[i], false);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private void writeField(BufferedWriter writer, String field, int value, boolean isSeparator)
            throws IOException {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(field).append(",").append(value);
        if (isSeparator) {
            reportBuilder.append(System.lineSeparator());
        }
        writer.write(reportBuilder.toString());
        writer.flush();
    }
}
