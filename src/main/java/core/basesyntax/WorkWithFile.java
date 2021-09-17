package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private int supplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        /* reading data from file */
        File fileToRead = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file ", e);
        }

        /*Creating report*/
        String[] lines = stringBuilder.toString().split(System.lineSeparator());
        for (String line: lines) {
            String[] lineParts = line.split(",");
            if (lineParts[0].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(lineParts[1]);
            } else if (lineParts[0].equals(BUY)) {
                buyTotal += Integer.parseInt(lineParts[1]);
            }
        }
        int result = supplyTotal - buyTotal;
        String[] linesToWrite = {"supply," + supplyTotal, "buy," + buyTotal, "result," + result};

        /* writing data to file */
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(fileToWrite, true))) {
            for (String line: linesToWrite) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file", e);
        }
    }
}
