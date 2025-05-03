package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    private static final byte RECORD_SPLIT_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readCsvFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String file, String data) {
        File uptputFile = new File(file);
        try {
            Files.write(uptputFile.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(String data) {
        StringBuilder report = new StringBuilder();
        int supply = 0;
        int buy = 0;
        String[] record = data.split(System.lineSeparator());
        for (String singleLineEntry : record) {
            String[] recordSplit = singleLineEntry.split(",");
            if (recordSplit[RECORD_SPLIT_INDEX].equalsIgnoreCase("buy")) {
                buy = buy + Integer.parseInt(recordSplit[1]);
            } else {
                supply = supply + Integer.parseInt(recordSplit[1]);
            }
        }
        int result = supply - buy;
        report.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).append(System.lineSeparator());
        return report.toString();
    }

    private String readCsvFile(String file) {
        File inputFile = new File(file);
        String csvFileContent;
        try {
            csvFileContent = Files.readString(inputFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cant read file " + inputFile, e);
        }
        return csvFileContent;
    }

}



