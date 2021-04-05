package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        String fileInfo = getDataFromFile(fileFrom);
        writeToNewFile(fileTo,fileInfo);
    }

    private String getDataFromFile(File fileFrom) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(COMMA);
                if (data[INDEX_OF_OPERATION_TYPE].equals(BUY)) {
                    buy += Integer.parseInt(data[INDEX_OF_AMOUNT]);
                }
                if (data[INDEX_OF_OPERATION_TYPE].equals(SUPPLY)) {
                    supply += Integer.parseInt(data[INDEX_OF_AMOUNT]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fileFrom, e);
        }
        return createReport(buy,supply);
    }

    private String createReport(int buy, int supply) {
        StringBuilder builder = new StringBuilder();
        int result = supply - buy;
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeToNewFile(File fileTo, String fileInfo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            writer.write(fileInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + fileTo, e);
        }
    }
}
