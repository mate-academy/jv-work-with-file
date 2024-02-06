package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        int supplyCount = 0;
        int buyCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String [] input = line.split(",");
                switch (input[0]) {
                    case "supply":
                        supplyCount += Integer.parseInt(input[1]);
                        break;
                    case "buy":
                        buyCount += Integer.parseInt(input[1]);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("problems with file!", e);
        }
        String result = "supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + (supplyCount - buyCount);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("problems with file!", e);
        }
    }
}
