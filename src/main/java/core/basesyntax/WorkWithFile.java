package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String DELIMITER = ",";

    private static final String SUPPLY_OPERATION = "supply";

    private static final String BUY_OPERATION = "buy";

    private static final int AMOUNT_INDEX = 1;

    private static final int WORD_INDEX = 0;

    private static final String RESULT_OPERATION = "result";

    private static final String LS = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String givenText = readTheFile(fromFileName);
        int[] digits = calculations(givenText);
        String result = buildReport(digits[0], digits[1]);
        writeIntoFile(result, toFileName);
    }

    private String readTheFile(String fromFileName) {
        String fileName = new File(fromFileName).getName();
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private int[] calculations(String givenText) {
        int supply = 0;
        int buy = 0;

        String[] lines = givenText.split("\\R");
        for (String line : lines) {
            if (line == null || line.isBlank()) {
                continue;
            }

            String[] oneLine = line.split(DELIMITER);
            if (oneLine.length < 2) {
                continue;
            }
            int number = Integer.parseInt(oneLine[AMOUNT_INDEX]);
            if (oneLine[WORD_INDEX].equals(SUPPLY_OPERATION)) {
                supply += number;
            } else if (oneLine[WORD_INDEX].equals(BUY_OPERATION)) {
                buy += number;
            }
        }

        return new int[]{supply, buy};
    }

    private String buildReport(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        int result = supply - buy;

        stringBuilder.append(SUPPLY_OPERATION).append(DELIMITER).append(supply)
                .append(LS).append(BUY_OPERATION).append(DELIMITER).append(buy)
                .append(LS).append(RESULT_OPERATION).append(DELIMITER).append(result);

        return stringBuilder.toString();
    }

    private void writeIntoFile(String result, String toFileName) {
        String fileName = new File(toFileName).getName();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(result);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
