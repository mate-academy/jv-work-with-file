package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";
    public static final int INDEX_FOR_OPERATION_TYPE = 0;
    public static final int INDEX_FOR_AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String textLine = bufferedReader.readLine();
            while (textLine != null) {
                String[] lineInfo = textLine.split(COMMA);
                if (lineInfo[INDEX_FOR_OPERATION_TYPE].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(lineInfo[INDEX_FOR_AMMOUNT]);
                } else if (lineInfo[INDEX_FOR_OPERATION_TYPE].equals(BUY)) {
                    buyAmount += Integer.parseInt(lineInfo[INDEX_FOR_AMMOUNT]);
                }
                textLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        writeToFile(toFileName, getReport(supplyAmount, buyAmount));
    }

    private String getReport(int supply, int buy) {
        return SUPPLY + COMMA + supply + System.lineSeparator()
                + BUY + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + (supply - buy);
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
