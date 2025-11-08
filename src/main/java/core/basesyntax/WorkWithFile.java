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
        int supplyAll = 0;
        int buyAll = 0;
        File file = createTargetFile(toFileName);

        for (String valueFromFile : readLines(fromFileName)) {
            String[] value = valueFromFile.split(",");
            if (value[INDEX_DATA_0].equals(SUPPLY_OPERATION)) {
                supplyAll += Integer.parseInt(value[INDEX_DATA_1]);
            }

            if (value[INDEX_DATA_0].equals(BUY_OPERATION)) {
                buyAll += Integer.parseInt(value[INDEX_DATA_1]);
            }
        }
        writToFiles(file, buildReport(supplyAll,buyAll));
    }

    private File createTargetFile(String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file",e);
        }
        return file;
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

    private void writToFiles(File file, String report) {
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
