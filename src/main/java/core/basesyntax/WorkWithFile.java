package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_VALUE = "supply";
    private static final String BUY_VALUE = "buy";
    private static final String RESULT_VALUE = "result";
    private static final String COMMA_VALUE = ",";
    private static final String WHITESPACE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        Path pathFromFile = Paths.get(fromFileName);
        Path pathToFileName = Paths.get(toFileName);
        String[] report = getReport(pathFromFile);
        saveReport(report, pathToFileName);
    }

    private void createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }

    private void saveReport(String[] report, Path path) {
        createFile(path);
        for (String line : report) {
            try {
                Files.write(path, line.getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                throw new RuntimeException("Can't write in file", e);
            }
        }
    }

    public String[] getReport(Path path) {
        int sumSupply = 0;
        int sumBuy = 0;

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataFromLine = line.split(COMMA_VALUE);
                int operationSum = Integer.parseInt(dataFromLine[AMOUNT_INDEX]);
                switch (dataFromLine[OPERATION_TYPE_INDEX]) {
                    case SUPPLY_VALUE:
                        sumSupply += operationSum;
                        break;
                    case BUY_VALUE:
                        sumBuy += operationSum;
                        break;
                    default:
                        throw new RuntimeException("Wrong data format");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return createResultArray(sumSupply, sumBuy);
    }

    private String[] createResultArray(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        return new String[]{
                stringBuilder.append(SUPPLY_VALUE).append(COMMA_VALUE)
                        .append(supply).append(WHITESPACE).toString(),
                stringBuilder.append(BUY_VALUE).append(COMMA_VALUE)
                        .append(buy).append(WHITESPACE).toString(),
                stringBuilder.append(RESULT_VALUE).append(COMMA_VALUE)
                        .append(result).toString()
        };
    }
}
