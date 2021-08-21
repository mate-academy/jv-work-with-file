package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int OPERATION_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final int LENGTH = 3;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        String[] dataLines = parseLine(readFile(fromFileName), System.lineSeparator());
        for (String line: dataLines) {
            String[] parseLine = parseLine(line, SEPARATOR);
            if (parseLine[OPERATION_INDEX].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(parseLine[COUNT_INDEX]);
            } else {
                buyCount += Integer.parseInt(parseLine[COUNT_INDEX]);
            }
        }
        int resultCount = supplyCount - buyCount;
        String[] statistic = new String[LENGTH];
        statistic[SUPPLY_INDEX] = SUPPLY + SEPARATOR + supplyCount + System.lineSeparator();
        statistic[BUY_INDEX] = BUY + SEPARATOR + buyCount + System.lineSeparator();
        statistic[RESULT_INDEX] = RESULT + SEPARATOR + resultCount;
        writeToFile(statistic,toFileName);
    }

    private String[] parseLine(String line, String separator) {
        return line.split(separator);
    }

    private String readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String[] linesToWrite, String toFile) {
        File file = new File(toFile);
        for (String line:linesToWrite) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
                bufferedWriter.write(line);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file" + e);
            }
        }
    }
}
