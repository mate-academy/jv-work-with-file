package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";
    private static final int NAME_OF_OPERATION = 0;
    private static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = this.readFile(fromFileName);
        String[][] processedData = processData(dataFromFile);
        String report = createReport(processedData);
        writeFile(toFileName, report);
    }

    private String readFile(String fileName) {
        final StringBuilder dataFromFile = new StringBuilder();
        final File fromFile = new File(fileName);
        String value;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return dataFromFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private void writeFile(String fileName, String data) {
        final File toFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String[][] processData(String data) {
        String[] splittedData = data.split(System.lineSeparator());
        String[][] processedData = new String[splittedData.length][2];
        for (int i = 0; i < splittedData.length; i++) {
            String[] tempo = splittedData[i].split(SEPARATOR);
            processedData[i][NAME_OF_OPERATION] = tempo[NAME_OF_OPERATION];
            processedData[i][VALUE] = tempo[VALUE];
        }
        return processedData;
    }

    private String createReport(String[][] processedData) {
        int supply = 0;
        int buy = 0;
        int result;
        String report;

        for (String[] strings : processedData) {
            if (strings[0].equals(SUPPLY)) {
                supply += Integer.parseInt(strings[1]);
            } else if (strings[0].equals(BUY)) {
                buy += Integer.parseInt(strings[1]);
            }
        }
        result = supply - buy;
        report = SUPPLY + SEPARATOR + supply + System.lineSeparator()
                + BUY + SEPARATOR + buy + System.lineSeparator()
                + RESULT + SEPARATOR + result;
        return report;
    }
}
