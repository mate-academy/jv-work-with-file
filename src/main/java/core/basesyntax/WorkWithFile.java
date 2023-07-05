package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String LINE_SEPARATOR = "\\r?\\n";
    public static final String COMA_SEPARATOR = ",";
    public static final int POSITION_OF_NAME_OF_OPERATION = 0;
    public static final int POSITION_OF_AMOUNT_OF_OPERATION = 1;
    public static final String SUPPLY_OPERATION = "supply";
    public static final String BUY_OPERATION = "buy";
    public static final String RESULT_OF_OPERATIONS = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String[] data = dataFromFile.split(LINE_SEPARATOR);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName,String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private String createReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String dataLine : data) {
            String nameOfOperation = dataLine.split(COMA_SEPARATOR)[POSITION_OF_NAME_OF_OPERATION];
            int amountOfOperation = Integer.parseInt(
                    dataLine.split(COMA_SEPARATOR)[POSITION_OF_AMOUNT_OF_OPERATION]);
            if (nameOfOperation.equals(SUPPLY_OPERATION)) {
                supply += amountOfOperation;
            } else if (nameOfOperation.equals(BUY_OPERATION)) {
                buy += amountOfOperation;
            }
        }
        int result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_OPERATION + COMA_SEPARATOR)
                .append(supply).append(System.lineSeparator());
        report.append(BUY_OPERATION + COMA_SEPARATOR)
                .append(buy).append(System.lineSeparator());
        report.append(RESULT_OF_OPERATIONS + COMA_SEPARATOR)
                .append(result);
        return report.toString();
    }
}
