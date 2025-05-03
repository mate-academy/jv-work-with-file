package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String RESULT_RECORD = "result";
    private static final String SUPPLY_TYPE = "supply";
    private static final String COMMA_SEPARATOR = ",";
    private static final String BUY_TYPE = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> records = readFromFile(fromFileName);
        String[] convertedRecords = recordsConverter(records);
        File report = createOrReplace(toFileName);
        writeRecordsToFile(convertedRecords, report);
    }

    public List<String> readFromFile(String fileName) {
        String dataFromFile;
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            dataFromFile = bufferedReader.readLine();
            while (dataFromFile != null) {
                fileContent.add(dataFromFile);
                dataFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
        return fileContent;
    }

    private File createOrReplace(String fileName) {
        File report = new File(fileName);
        try {
            Files.deleteIfExists(Path.of(fileName));
            report.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create or replace the file " + fileName, e);
        }
        return report;

    }

    private String[] recordsConverter(List<String> records) {
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
