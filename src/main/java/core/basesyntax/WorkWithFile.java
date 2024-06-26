package core.basesyntax;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";

    private final CsvFileReader reader = new CsvFileReader();
    private final CsvFileWriter writer = new CsvFileWriter();

    public void getStatistic(String fromFileName, String toFileName) {
        Path pathFile = Paths.get(fromFileName);
        List<String> result = reader.readCsvFileWithData(pathFile);

        int buyCount = 0;
        int supplyCount = 0;

        for (String line : result) {
            String[] parts = line.split(",");
            if (BUY.equals(parts[0])) {
                buyCount += Integer.parseInt(parts[1]);
            }
            if (SUPPLY.equals(parts[0])) {
                supplyCount += Integer.parseInt(parts[1]);
            }
        }

        int total = supplyCount - buyCount;
        writer.createCsvFile(toFileName);
        writer.writeCsvFileWithData(new File(toFileName).toPath(), buyCount, supplyCount, total);
    }
}
