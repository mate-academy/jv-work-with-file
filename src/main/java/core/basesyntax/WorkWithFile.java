package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    // CHECKLIST ITEM #6: Declared constants for repeated strings
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String RESULT_KEY = "result";
    private static final String COMMA = ",";

    // CHECKLIST ITEM #1: Single method responsibility (Orchestrator)
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);
        String report = createReport(data);
        writeReport(report, toFileName);
    }

    // Extracts reading logic. Opens and closes reading resources quickly.
    private String readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            // CHECKLIST ITEM #9: Specific exception message including the filename
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        return stringBuilder.toString();
    }

    // Extracts parsing and calculation logic. Has no file I/O operations.
    private String createReport(String data) {
        int buy = 0;
        int supply = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            if (!line.isEmpty()) {
                String[] array = line.split(COMMA);
                if (array[0].equals(OPERATION_BUY)) {
                    buy += Integer.parseInt(array[1]);
                } else if (array[0].equals(OPERATION_SUPPLY)) {
                    supply += Integer.parseInt(array[1]);
                }
            }
        }

        int result = supply - buy;

        return OPERATION_SUPPLY + COMMA + supply + System.lineSeparator()
                + OPERATION_BUY + COMMA + buy + System.lineSeparator()
                + RESULT_KEY + COMMA + result;
    }

    // Extracts writing logic. Opens and closes writing resources quickly.
    private void writeReport(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
