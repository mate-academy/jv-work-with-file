package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyNumber = 0;
        int buyNumber = 0;
        int resultNumber = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] stringLine = value.split(",");
                if (stringLine[0].equals(SUPPLY) == true) {
                    supplyNumber += Integer.parseInt(stringLine[1]);
                }
                if (stringLine[0].equals(BUY) == true) {
                    buyNumber += Integer.parseInt(stringLine[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can`t read");
        }
        String output = SUPPLY + "," + supplyNumber + System.lineSeparator() + BUY + ","
                + buyNumber + System.lineSeparator() + RESULT + "," + (supplyNumber - buyNumber);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(output);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("File can`t write");
        }
    }
}
