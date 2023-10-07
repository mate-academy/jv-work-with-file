package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File sourceFile = new File(fromFileName);
        File destinationfile = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String readerString = reader.readLine();
            int supplyCount = 0;
            int buyCount = 0;
            int operatedAmount;
            String[] splittedLine;
            while (readerString != null) {
                splittedLine = readerString.split(",");
                operatedAmount = Integer.parseInt(splittedLine[1]);
                if (splittedLine[0].equals("supply")) {
                    supplyCount += operatedAmount;
                } else {
                    buyCount += operatedAmount;
                }
                readerString = reader.readLine();
            }
            String resultData = "supply," + supplyCount + System.lineSeparator()
                    + "buy," + buyCount + System.lineSeparator()
                    + "result," + (supplyCount - buyCount);
            Files.write(destinationfile.toPath(), resultData.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Statistics can't be generated", e);
        }
    }
}

