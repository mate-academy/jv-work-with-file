package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SECOND_ELEMENT_OF_ARRAY = 1;
    private static final String BUY = "buy,";
    private static final String SUPPLY = "supply,";
    private static final String RESULT = "result,";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int bye = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineOfFileName = bufferedReader.readLine();
            while (lineOfFileName != null) {
                if (lineOfFileName.contains(BUY)) {
                    bye += Integer.parseInt(lineOfFileName
                            .split(SEPARATOR)[SECOND_ELEMENT_OF_ARRAY]);
                } else {
                    supply += Integer.parseInt(lineOfFileName
                            .split(SEPARATOR)[SECOND_ELEMENT_OF_ARRAY]);
                }
                lineOfFileName = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writeToFileName = new BufferedWriter(new FileWriter(toFileName))) {
            writeToFileName.write(SUPPLY + supply + System.lineSeparator()
                    + BUY + bye + System.lineSeparator() + RESULT + (supply - bye));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
