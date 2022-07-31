package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String string = bufferedReader.readLine();
            while (string != null) {
                if (string.substring(0, string.indexOf(REGEX)).equals(SUPPLY)) {
                    supply += Integer.parseInt(string.substring(string.indexOf(REGEX) + 1));
                }
                if (string.substring(0, string.indexOf(REGEX)).equals(BUY)) {
                    buy += Integer.parseInt(string.substring(string.indexOf(REGEX) + 1));
                }
                string = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result," + result);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }
}
