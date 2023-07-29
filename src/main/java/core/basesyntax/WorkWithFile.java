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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            int supply = 0;
            int buy = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                final String[] words = line.split(",");
                if (SUPPLY.equals(words[0])) {
                    supply += Integer.parseInt(words[1]);
                } else if (BUY.equals(words[0])) {
                    buy += Integer.parseInt(words[1]);
                }
            }
            int result = supply - buy;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator());
            stringBuilder.append(BUY).append(",").append(buy).append(System.lineSeparator());
            stringBuilder.append(RESULT).append(",").append(result).append(System.lineSeparator());

            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read/write file.", e);
        }
    }
}
