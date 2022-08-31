package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int OPERATION_INDEX = 0;
    static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(final String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder result = new StringBuilder();

        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)

        ) {
            String nextString = bufferedReader.readLine();
            while (nextString != null) {
                result.append(nextString);
                result.append(System.lineSeparator());
                nextString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return result.toString();
    }

    private String createReport(final String dataFromFile) {
        StringBuilder result = new StringBuilder();
        int supply = 0;
        int buy = 0;
        String[] dataFromFileArray = dataFromFile.split(System.lineSeparator());
        for (final String line : dataFromFileArray) {
            String[] datumArray = line.split(",");
            String operation = datumArray[OPERATION_INDEX];
            int amount = Integer.parseInt(datumArray[AMOUNT_INDEX]);
            if (operation.equals("supply")) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        result.append("supply")
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(supply - buy);
        return result.toString();
    }

    private void writeToFile(final String report, final String toFileName) {
        try (
                FileWriter fileWriter = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
