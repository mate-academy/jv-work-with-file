package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);
        List<String> values;
        List<String> reports = new ArrayList<>();

        try {
            values = Files.readAllLines(readFile.toPath());
        } catch (IOException ex) {
            throw new RuntimeException("Can not read from file");
        }

        int supplySum = 0;
        int buySum = 0;

        for (int i = 0; i < values.size(); i++) {
            String[] splitValue = values.get(i).split(",");
            if (splitValue[0].equals("supply")) {
                supplySum += Integer.parseInt(splitValue[1]);
            }

            if (splitValue[0].equals("buy")) {
                buySum += Integer.parseInt(splitValue[1]);
            }
        }

        reports.add("supply," + supplySum);
        reports.add("buy," + buySum);
        reports.add("result," + (supplySum - buySum));

        try {
            Files.write(writeFile.toPath(), reports);

        } catch (IOException ex) {
            throw new RuntimeException("Can not write to file");
        }
    }
}

