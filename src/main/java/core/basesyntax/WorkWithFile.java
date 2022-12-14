package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputArray;
        try {
            inputArray = Files.readString(Paths.get(fromFileName)).split("\n");
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        int supplyCount = 0;
        int buyCount = 0;
        for (int i = 0; i < inputArray.length; i++) {
            String [] temp = inputArray[i].split(",");
            int tempInt = Integer.parseInt(temp[1].replaceAll("[^\\d]", ""));
            if (temp[0].contains("buy")) {
                buyCount += tempInt;
            } else {
                supplyCount += tempInt;
            }
        }
        StringBuilder outputData = new StringBuilder();
        outputData.append("supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator() + "result,"
                + (supplyCount - buyCount));
        try {
            FileWriter fileWriter = new FileWriter(toFileName);
            fileWriter.write(outputData.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't open file", e);
        }
    }
}
