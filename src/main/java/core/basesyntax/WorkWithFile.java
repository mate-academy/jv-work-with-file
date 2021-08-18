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

        try (BufferedReader bufferedReaderreader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReaderreader.readLine();
            final int suppliedPosition = 1;
            final int boughtPosition = 1;

            while (line != null) {
                if (line.split(",")[0].equals("supply")) {
                    suppliedNumber += Integer.parseInt(line.split(",")[suppliedPosition]);
                } else if (line.split(",")[0].equals("buy")) {
                    boughtNumber += Integer.parseInt(line.split(",")[boughtPosition]);
                }

                line = bufferedReaderreader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read data from the file.", e);
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("supply").append(",").append(suppliedNumber)
                .append(System.lineSeparator())
                .append("buy").append(",").append(boughtNumber)
                .append(System.lineSeparator())
                .append("result").append(",").append(suppliedNumber - boughtNumber);

        return stringBuilder.toString();
    }

    private void writeData(String data, String fileName) {
        for (String dataRow : data.split(System.lineSeparator())) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(fileName, true))) {
                bufferedWriter.write(dataRow);
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Couldn't write data to the file.", e);

            }
        }
    }
}
