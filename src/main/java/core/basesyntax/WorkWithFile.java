package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int ACTION = 0;
    private static final int AMOUNT = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromCsvFile(fromFileName);
        writeReportToCsvFile(toFileName);
    }

    private void readDataFromCsvFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            buy = 0;
            supply = 0;
            while (line != null) {
                String[] value = line.split(",");
                switch (value[ACTION]) {
                    case BUY:
                        buy += Integer.parseInt(value[AMOUNT]);
                        break;
                    case SUPPLY:
                        supply += Integer.parseInt(value[AMOUNT]);
                        break;
                    default:
                        System.err.println("Corrupted data in input file");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private void writeReportToCsvFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator());
        stringBuilder.append(BUY).append(",").append(buy).append(System.lineSeparator());
        stringBuilder.append(RESULT).append(",").append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
