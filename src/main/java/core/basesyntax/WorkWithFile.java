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
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;
    private String fromFileName;
    private String toFileName;

    public String getFromFileName() {
        return fromFileName;
    }

    public String getToFileName() {
        return toFileName;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        this.fromFileName = fromFileName;
        this.toFileName = toFileName;
        writeToFile();
    }

    private String readFile() {
        File fileToRead = new File(getFromFileName());
        StringBuilder builderFromFileName = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
            String value = reader.readLine();
            while (value != null) {
                builderFromFileName.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + getFromFileName(), e);
        }
        return builderFromFileName.toString();
    }

    private String createReport() {
        String[] splitLineSeparator = readFile().split(System.lineSeparator());
        int supply = 0;
        int buy = 0;

        for (String s : splitLineSeparator) {
            String[] commaSeparatedData = s.split(",");
            if (commaSeparatedData[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supply = supply + Integer.parseInt(commaSeparatedData[AMOUNT_INDEX]);
            }
            if (commaSeparatedData[OPERATION_TYPE_INDEX].equals(BUY)) {
                buy = buy + Integer.parseInt(commaSeparatedData[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;

        StringBuilder recordBuilder = new StringBuilder();
        recordBuilder.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(",").append(buy)
                .append(System.lineSeparator())
                .append("result").append(",").append(result);
        return recordBuilder.toString();
    }

    private void writeToFile() {
        File reportFile = new File(getToFileName());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile, true))) {
            bufferedWriter.write(createReport());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + getToFileName(), e);
        }
    }
}
