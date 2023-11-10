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
    private static final String SEPARATE = ",";
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supply = 0;
            int buy = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] files = line.split(SEPARATE);
                if (files[OPERATION].equals(SUPPLY)) {
                    supply += Integer.parseInt(files[AMOUNT]);
                } else if (files[OPERATION].equals(BUY)) {
                    buy += Integer.parseInt(files[AMOUNT]);
                }
            }
            int result = supply - buy;

            bufferedWriter.write(SUPPLY + SEPARATE + supply);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + SEPARATE + buy);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + SEPARATE + result);

        } catch (IOException e) {
            throw new RuntimeException("Can`t read/write files", e);
        }
    }
}
