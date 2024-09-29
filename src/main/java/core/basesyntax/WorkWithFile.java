package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int supplySum = 0;
        int buySum = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] words = value.split(",");
                if (words[OPERATION_TYPE].equals("supply")) {
                    supplySum += Integer.parseInt(words[AMOUNT]);
                } else if (words[OPERATION_TYPE].equals("buy")) {
                    buySum += Integer.parseInt(words[AMOUNT]);
                }
                value = bufferedReader.readLine();
            }
            result += (supplySum - buySum);

            String report = "supply," + supplySum + System.lineSeparator()
                    + "buy," + buySum + System.lineSeparator()
                    + "result," + result + System.lineSeparator();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
