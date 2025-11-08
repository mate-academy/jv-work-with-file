package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_DATA_0 = 0;
    private static final int INDEX_DATA_1 = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        int[] sums = {0,0};
        File file = new File(toFileName);
        getSum(fromFileName, sums);
        writeToFiles(file, buildReport(sums[INDEX_DATA_0],sums[INDEX_DATA_1]));
    }

    private void getSum(String fromFileName, int[] sums) {
        for (String valueFromFile : readLines(fromFileName)) {
            String[] value = valueFromFile.split(",");
            if (value[INDEX_DATA_0].equals(SUPPLY_OPERATION)) {
                sums[INDEX_DATA_0] += Integer.parseInt(value[INDEX_DATA_1]);
            }

            if (value[INDEX_DATA_0].equals(BUY_OPERATION)) {
                sums[INDEX_DATA_1] += Integer.parseInt(value[INDEX_DATA_1]);
            }
        }
    }

    private String[] readLines(String fromFileName) {
        String[] valuesFromFile;
        Path pathFromFile = Path.of(fromFileName);
        try {
            List<String> lines = Files.readAllLines(pathFromFile);
            valuesFromFile = lines.toArray(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        }
        return valuesFromFile;
    }

    private String buildReport(int supply, int buy) {
        StringBuilder builder = new StringBuilder();

        builder.append(SUPPLY_OPERATION).append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY_OPERATION)
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT_OPERATION)
                .append(",")
                .append(supply - buy);
        return builder.toString();
    }

    private void writeToFiles(File file, String report) {
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
