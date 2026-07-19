package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileToRead = new File(fromFileName);
        int supplyTotal = 0;
        int buyTotal = 0;
        try {
            List<String> strings = Files.readAllLines(fileToRead.toPath());
            for (String string : strings) {
                String[] stringSplit = string.split(",");

                if (stringSplit[0].equals("supply")) {
                    int supply = Integer.parseInt(stringSplit[1]);
                    supplyTotal += supply;
                } else if (stringSplit[0].equals("buy")) {
                    int buy = Integer.parseInt(stringSplit[1]);
                    buyTotal += buy;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int sum = supplyTotal - buyTotal;
        List<String> results = new ArrayList<>();
        results.add("supply" + "," + supplyTotal);
        results.add("buy" + "," + buyTotal);
        results.add("result" + "," + sum);

        File fileToWrite = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((fileToWrite)))) {
            for (String result : results) {
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
