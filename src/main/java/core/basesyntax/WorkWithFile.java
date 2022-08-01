package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private String line;
    private int buy = 0;
    private int supply = 0;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {

        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String [] reader = line.split(",");
                if (reader[0].equals(SUPPLY)) {
                    supply = supply + Integer.parseInt(reader[1]);
                } else if (reader[0].equals(BUY)) {
                    buy = buy + Integer.parseInt(reader[1]);
                }
                result = supply - buy;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(System.lineSeparator() + "supply" + "," + supply);
            bufferedWriter.write(System.lineSeparator() + "buy" + "," + buy);
            bufferedWriter.write(System.lineSeparator() + "result" + "," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + toFileName, e);
        }
    }
}
