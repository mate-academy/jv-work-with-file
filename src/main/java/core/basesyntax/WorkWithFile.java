package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_TYPE = "supply";
    private static final String SPLIT_WORDS = ",";
    private static final int FIRST_CELL = 0;
    private static final int SECOND_CELL = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    public void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file ", e);
        }
    }

    private String readFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(SPLIT_WORDS);
                if (split[FIRST_CELL].equals(OPERATION_TYPE)) {
                    supply += Integer.parseInt(split[SECOND_CELL]);
                } else {
                    buy += Integer.parseInt(split[SECOND_CELL]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
        return stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                .append(supply - buy).toString();
    }
}
