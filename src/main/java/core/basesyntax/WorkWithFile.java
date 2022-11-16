package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT = ",";
    private static final int ACTION = 0;
    private static final int DATA = 1;
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] split = value.split(SPLIT);
                if ((split[ACTION]).equals(BUY)) {
                    buy += Integer.parseInt(split[DATA]);
                } else {
                    supply += Integer.parseInt(split[DATA]);
                }
            }
        } catch (IOException e) {
            System.out.printf("Can't read to file " + fromFileName);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (supply - buy));
        } catch (IOException e) {
            System.out.printf("Can't write to file " + toFileName);
        }
    }
}
