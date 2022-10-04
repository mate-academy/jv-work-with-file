package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFile(fromFileName);
        String report = createReport(content);
        writeToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        File fileToRead = new File(fileName);
        StringBuilder builderFromFileName = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String value = reader.readLine();
            while (value != null) {
                builderFromFileName.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fileName, e);
        }
        return builderFromFileName.toString();
    }

    private String createReport(String data) {
        String[] splitLineSeparator = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;

        for (String s : splitLineSeparator) {
            String[] commaSeparatedData = s.split(COMMA);
            if (commaSeparatedData[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supply = supply + Integer.parseInt(commaSeparatedData[AMOUNT_INDEX]);
            }
            if (commaSeparatedData[OPERATION_TYPE_INDEX].equals(BUY)) {
                buy = buy + Integer.parseInt(commaSeparatedData[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;

        StringBuilder recordBuilder = new StringBuilder();
        recordBuilder.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return recordBuilder.toString();
    }

    private void writeToFile(String fileName, String data) {
        File reportFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + fileName, e);
        }
    }
}
