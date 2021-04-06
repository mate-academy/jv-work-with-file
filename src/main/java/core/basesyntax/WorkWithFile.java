package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final int FIRST_ELEMENT = 1;
    private static final int ZERO_ELEMENT = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String finalString = readStatistic(fromFileName);
        writeToFile(finalString, toFileName);
    }

    public String readStatistic(String fromFileName) {

        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringArray = line.split(CSV_SEPARATOR);
                if (stringArray[ZERO_ELEMENT].equals(SUPPLY_WORD)) {
                    supply += Integer.parseInt(stringArray[FIRST_ELEMENT]);
                } else if (stringArray[ZERO_ELEMENT].equals(BUY_WORD)) {
                    buy += Integer.parseInt(stringArray[FIRST_ELEMENT]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        }
        String report = SUPPLY_WORD + "," + supply + System.lineSeparator()
                + BUY_WORD + "," + buy + System.lineSeparator()
                + "result" + "," + (supply - buy);
        return report;
    }
    
    public void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File not found " + toFileName, e);
        }
    }
}
