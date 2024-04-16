package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));

            String line;

            int buyVal = 0;
            int supplyVal = 0;

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArr = line.split(",");
                if (Objects.equals(lineArr[0], "buy")) {
                    buyVal += Integer.parseInt(lineArr[1]);
                } else if (Objects.equals(lineArr[0], "supply")) {
                    supplyVal += Integer.parseInt(lineArr[1]);
                }
            }
            bufferedReader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            String stringBuilder = "supply," + supplyVal + System.lineSeparator()
                    + "buy," + buyVal + System.lineSeparator()
                    + "result," + (supplyVal - buyVal);
            writer.write(stringBuilder);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
