package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] newInfoForSupplyAndBuy = readFile(fromFileName);
        writeFile(toFileName, newInfoForSupplyAndBuy);
    }

    private void writeFile(String toFileName, int[] information) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String stringBuilder = SUPPLY + COMA + information[0] + System.lineSeparator()
                    + BUY + COMA + information[1] + System.lineSeparator()
                    + RESULT + COMA + (information[0] - information[1]);
            bufferedWriter.write(stringBuilder);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data from the file " + toFileName, e);
        }
    }

    private int[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int[] infoForSupplyAndBuy = new int[2];
            String info = reader.readLine();
            while (info != null) {
                String[] strings = info.split(COMA);
                if (strings[0].equals(SUPPLY)) {
                    infoForSupplyAndBuy[0] += Integer.parseInt(strings[1]);
                } else if (strings[0].equals(BUY)) {
                    infoForSupplyAndBuy[1] += Integer.parseInt(strings[1]);
                }
                info = reader.readLine();
            }
            return infoForSupplyAndBuy;
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }
}

