package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY = 0;
    private static final int INDEX_OF_VALUE = 1;
    private static final String VALUE_SPLITTER = ",";
    private static final String SUPPLY_VALUE = "supply";
    private static final String RESULT = "result";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String stringWithResult;
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] stringsFromLine = value.split(VALUE_SPLITTER);
                if (stringsFromLine[INDEX_OF_SUPPLY].equals(SUPPLY_VALUE)) {
                    supply += Integer.parseInt(stringsFromLine[INDEX_OF_VALUE]);
                } else {
                    buy += Integer.parseInt(stringsFromLine[INDEX_OF_VALUE]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write from file");
        }

        stringWithResult = createString(supply,buy);
        writeDataFromFile(toFileName,stringWithResult);

    }

    private String createString(int supply, int buy) {
        StringBuilder returnedStringToFile = new StringBuilder();
        returnedStringToFile.append(SUPPLY_VALUE).append(VALUE_SPLITTER).append(supply).append("\n")
                .append(BUY).append(VALUE_SPLITTER).append(buy).append("\n")
                .append(RESULT).append(VALUE_SPLITTER).append(supply - buy);
        return returnedStringToFile.toString();
    }

    private void writeDataFromFile(String toFileName, String dataName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(dataName);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file");
        }
    }
}
