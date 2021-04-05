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

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        String fileInfo = getFileInfo(fileFrom);
        writeToNewFile(fileTo,fileInfo);
    }

    private String getFileInfo(File fileFrom) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String value = reader.readLine();
            while (value != null) {
                int indexOfComma = value.indexOf(COMMA);
                String operationType = value.substring(0,indexOfComma);
                int amount = Integer.parseInt(value.substring(indexOfComma + 1, value.length()));
                if (operationType.equals(BUY)) {
                    buy += amount;
                }
                if (operationType.equals(SUPPLY)) {
                    supply += amount;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fileFrom, e);
        }
        return getCvsFormat(buy,supply);
    }

    private String getCvsFormat(int buy, int supply) {
        StringBuilder builder = new StringBuilder();
        int result = supply - buy;
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeToNewFile(File fileTo,String fileInfo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            writer.write(fileInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + fileTo, e);
        }
    }
}
