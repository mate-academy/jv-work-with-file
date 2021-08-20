package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int REQUIRED_POSITION = 1;
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String finalData = getDataFromFile(fromFileName);
        writeData(finalData, toFileName);
    }

    private String getDataFromFile(String fileName) {
        int suppliedNumber = 0;
        int boughtNumber = 0;
        try (BufferedReader bufferedReaderreader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReaderreader.readLine();
            while (line != null) {
                if (line.split(COMMA)[OPERATION_TYPE_POSITION].equals(SUPPLY)) {
                    suppliedNumber += Integer.parseInt(line.split(COMMA)[REQUIRED_POSITION]);
                } else if (line.split(COMMA)[OPERATION_TYPE_POSITION].equals(BUY)) {
                    boughtNumber += Integer.parseInt(line.split(COMMA)[REQUIRED_POSITION]);
                }
                line = bufferedReaderreader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read data from the file " + fileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(suppliedNumber)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(boughtNumber)
                .append(System.lineSeparator())
                .append("result").append(COMMA).append(suppliedNumber - boughtNumber);
        return stringBuilder.toString();
    }

    private void writeData(String data, String fileName) {
        for (String dataRow : data.split(System.lineSeparator())) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(fileName, true))) {
                bufferedWriter.write(dataRow);
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Couldn't write data to the file " + fileName, e);
            }
        }
    }
}
