package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final short ELEMENT_INDEX = 0;
    private static final short VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] lineElements = currentLine.split(",");
                if (lineElements[ELEMENT_INDEX].equals(BUY)) {
                    buy += Integer.parseInt(lineElements[VALUE_INDEX]);
                } else if (lineElements[ELEMENT_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(lineElements[VALUE_INDEX]);
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.append(SUPPLY)
                    .append(",")
                    .append(String.valueOf(supply))
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(",")
                    .append(String.valueOf(buy))
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(",")
                    .append(String.valueOf(supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}
