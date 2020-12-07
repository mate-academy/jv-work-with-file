package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    
    public static final int ZERO_INDEX = 0;
    public static final int FIRST_INDEX = 1;
    public static final String DELIMITER = ",";
    public static final String RESULT = "result";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] info = line.split(DELIMITER);
                if (info[ZERO_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(info[FIRST_INDEX]);
                }
                if (info[ZERO_INDEX].equals(BUY)) {
                    buy += Integer.parseInt(info[FIRST_INDEX]);
                }
                line = reader.readLine();
            }
            createResultFile(toFileName, supply, buy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void createResultFile(String toFileName, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + "," + supply + System.lineSeparator()
                    + BUY + "," + buy + System.lineSeparator() + RESULT + "," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
