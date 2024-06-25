package core.basesyntax;

import java.io.File;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Reader reader = new Reader();
        Writer writer = new Writer();
        writer.createCsvFile(toFileName);
        File readFile = new File(fromFileName);
        File fromFile = new File(toFileName);
        List<String> result = reader.readCsvFileWithData(readFile.toPath());
        int buyCount = 0;
        int supplyCount = 0;
        int total;

        for (String line : result) {
            String[] parts = line.split(",");
            if (parts[0].equals("buy")) {
                buyCount += Integer.parseInt(parts[1]);
            }
            if (parts[0].equals("supply")) {
                supplyCount += Integer.parseInt(parts[1]);
            }
        }

        total = supplyCount - buyCount;
        writer.writeCsvFileWithData(fromFile.toPath(), buyCount, supplyCount, total);
    }
}
