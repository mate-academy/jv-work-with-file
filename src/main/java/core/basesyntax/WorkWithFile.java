package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String finalData = getDataFromFile(fromFileName);
        writeData(finalData, toFileName);
    }

    private String getDataFromFile(String fileName) {
        int suppliedNumber = 0;
        int boughtNumber = 0;
        final String supply = "supply";
        final String buy = "buy";
        final String comma = ",";

        try (BufferedReader bufferedReaderreader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReaderreader.readLine();
            final int requiredPosition = 1;
            final int operationTypePosition = 0;
            while (line != null) {
                if (line.split(comma)[operationTypePosition].equals(supply)) {
                    suppliedNumber += Integer.parseInt(line.split(comma)[requiredPosition]);
                } else if (line.split(comma)[operationTypePosition].equals(buy)) {
                    boughtNumber += Integer.parseInt(line.split(comma)[requiredPosition]);
                }
                line = bufferedReaderreader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read data from the file " + fileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(supply).append(comma).append(suppliedNumber)
                .append(System.lineSeparator())
                .append(buy).append(comma).append(boughtNumber)
                .append(System.lineSeparator())
                .append("result").append(comma).append(suppliedNumber - boughtNumber);
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
