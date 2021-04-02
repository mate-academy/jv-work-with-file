package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SPLITTER = ",";
    private static final String WORD_FOR_OUTPUT = "result,";
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeTofile(report, toFileName);
    }

    private void writeTofile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String createReport(String fromFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                String[] valueContent = value.split(SPLITTER);
                if (valueContent[OPERATION_TYPE].equals(SUPPLY)) {
                    supply += Integer.parseInt(valueContent[AMMOUNT]);
                } else {
                    buy += Integer.parseInt(valueContent[AMMOUNT]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return SUPPLY + SPLITTER + supply + System.lineSeparator()
                + BUY + SPLITTER + buy + System.lineSeparator()
                + WORD_FOR_OUTPUT + (supply - buy);
    }
}
