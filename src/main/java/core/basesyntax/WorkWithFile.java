package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMA = ",";
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
                switch (value[ZERO_INDEX]) {
                    case BUY:
                        buy += Integer.parseInt(value[FIRST_INDEX]);
                        break;
                    case SUPPLY:
                        supply += Integer.parseInt(value[FIRST_INDEX]);
                        break;
                    default:
                        System.err.println("Corrupted data in input file");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fileName, e);
        }
    }

    private void writeReportToCsvFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file" + fileName, e);
        }
    }
}
