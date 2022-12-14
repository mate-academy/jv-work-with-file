package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String RESULT_RECORD = "result";
    private static final String SUPPLY_TYPE = "supply";
    private static final String COMMA_SEPARATOR = ",";
    private static final String BUY_TYPE = "buy";
    private static final String EMPTY_STRING = "";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] records = readFromFile(fromFileName);
        String[] convertedRecords = recordsConverter(records);
        File report = createOrReplace(toFileName);
        writeRecordsToFile(convertedRecords, report);
    }

    public String[] readFromFile(String fileName) {
        String dataFromFile = EMPTY_STRING;
        StringBuilder readerStringBuilder;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            readerStringBuilder = new StringBuilder();
            dataFromFile = bufferedReader.readLine();
            while (dataFromFile != null) {
                readerStringBuilder.append(dataFromFile).append(System.lineSeparator());
                dataFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return readerStringBuilder.toString().split(System.lineSeparator());
    }

    private File createOrReplace(String fileName) {
        File report = new File(fileName);
        if (report.exists()) {
            report.delete();
        }
        try {
            report.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;

    }

    private String[] recordsConverter(String[] records) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String record : records) {
            String[] values = record.split(COMMA_SEPARATOR);
            if (values[TYPE_INDEX].equals(SUPPLY_TYPE)) {
                supply += Integer.parseInt(values[VALUE_INDEX]);
            } else {
                buy += Integer.parseInt(values[VALUE_INDEX]);
            }
        }
        result = supply - buy;
        return new String[]{SUPPLY_TYPE + COMMA_SEPARATOR + supply + System.lineSeparator(),
                BUY_TYPE + COMMA_SEPARATOR + buy + System.lineSeparator(),
                RESULT_RECORD + COMMA_SEPARATOR + result + System.lineSeparator()};
    }

    private void writeRecordsToFile(String[] convertedRecords, File report) {
        for (String record : convertedRecords) {
            try {
                Files.write(report.toPath(), record.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        }
    }
}
