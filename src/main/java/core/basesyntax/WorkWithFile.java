package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY_REPRESENT = "supply";
    private static final String BUY_REPRESENT = "buy";
    private static final String RESULT_REPRESENT = "result";
    private static final String VALUE_SEPARATOR = ",";
    private static final int DATA_DESIGNATION_INDEX = 0;
    private static final int DATA_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Path pathFromFile = Paths.get(fromFileName);
        Path pathToFileName = Paths.get(toFileName);

        String[] report = getReportFromFile(pathFromFile);
        saveReportToFile(report, pathToFileName);
    }

    private void saveReportToFile(String[] report, Path path) {
        createFile(path);

        for (String line : report) {
            try {
                Files.write(path, line.getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                throw new RuntimeException("Error of writing in file", e);
            }
        }
    }

    private void createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException("Error of creating file", e);
        }
    }

    public String[] getReportFromFile(Path path) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] dataFromLine = line.split(VALUE_SEPARATOR);
                int valueInLine = Integer.parseInt(dataFromLine[DATA_VALUE_INDEX]);

                switch (dataFromLine[DATA_DESIGNATION_INDEX]) {
                    case SUPPLY_REPRESENT:
                        supply += valueInLine;
                        break;
                    case BUY_REPRESENT:
                        buy += valueInLine;
                        break;
                    default:
                        throw new RuntimeException("File is not represent data format");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error of reading file", e);
        }

        return createStringArrayWithData(supply, buy);
    }

    private String[] createStringArrayWithData(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();

        return new String[]{
                stringBuilder.append(SUPPLY_REPRESENT).append(VALUE_SEPARATOR)
                        .append(supply).append(System.lineSeparator()).toString(),
                stringBuilder.append(BUY_REPRESENT).append(VALUE_SEPARATOR)
                        .append(buy).append(System.lineSeparator()).toString(),
                stringBuilder.append(RESULT_REPRESENT).append(VALUE_SEPARATOR)
                        .append(result).toString()
        };
    }
}
