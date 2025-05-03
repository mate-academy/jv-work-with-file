package core.basesyntax;

import java.io.File;
import java.util.List;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final int INDEX_OF_OPERATION_TYPE = 0;
    public static final int INDEX_OF_AMOUNT = 1;

    private final CsvFileReader reader = new CsvFileReader();
    private final CsvFileWriter writer = new CsvFileWriter();

    private static String processData(List<String> data) {
        int buyCount = 0;
        int supplyCount = 0;

        for (String line : data) {
            String[] parts = line.split(",");
            if (BUY.equals(parts[INDEX_OF_OPERATION_TYPE])) {
                buyCount += Integer.parseInt(parts[INDEX_OF_AMOUNT]);
            }
            if (SUPPLY.equals(parts[INDEX_OF_OPERATION_TYPE])) {
                supplyCount += Integer.parseInt(parts[INDEX_OF_AMOUNT]);
            }
        }
        return String.format("supply,%d\nbuy,%d\nresult,%d",
                supplyCount, buyCount, supplyCount - buyCount);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = reader.readCsvFileWithData(new File(fromFileName).toPath());
        String result = processData(dataFromFile);
        writer.writeToFile(result, toFileName);
    }
}
