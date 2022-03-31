package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        write(toFileName, calculate(read(fromFileName)));
    }

    private List<String> read(String fromFileName) {
        File readFile = new File(fromFileName);

        try {
            return Files.readAllLines(readFile.toPath());
        } catch (IOException ex) {
            throw new RuntimeException("Can not read from file");
        }
    }

    private void write(String toFileName, List<String> data) {
        File writeFile = new File(toFileName);

        try {
            Files.write(writeFile.toPath(), data);

        } catch (IOException ex) {
            throw new RuntimeException("Can not write to file");
        }
    }

    private List<String> calculate(List<String> data) {
        List<String> reports = new ArrayList<>();
        int supplySum = 0;
        int buySum = 0;

        for (int i = 0; i < data.size(); i++) {
            String[] splitValue = data.get(i).split(",");
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

        return reports;
    }
}



