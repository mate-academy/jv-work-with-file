package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String OPERATION_TYPE_SUPPLY = "supply";
    public static final String OPERATION_TYPE_BUY = "buy";
    public static final String COMA_DELIMITER = ",";
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeDataToFile(toFileName, report);
    }

    public String createReport(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int supply = 0;
            int buy = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] linePeaces = line.split(COMA_DELIMITER);
                if (linePeaces[OPERATION_TYPE_INDEX].equals(OPERATION_TYPE_SUPPLY)) {
                    supply += getIntFromString(linePeaces[AMOUNT_INDEX]);
                }
                if (linePeaces[OPERATION_TYPE_INDEX].equals(OPERATION_TYPE_BUY)) {
                    buy += getIntFromString(linePeaces[AMOUNT_INDEX]);
                }
                line = reader.readLine();
            }
            return "supply," + supply + "\n" + "buy," + buy + "\n" + "result," + (supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can not read fileName " + fileName, e);
        }
    }

    public void writeDataToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file " + fileName, e);
        }
    }

    public int getIntFromString(String number) {
        return Integer.parseInt(number);
    }
}
