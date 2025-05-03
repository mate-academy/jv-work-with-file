package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private String makeReport(String fromFileName) {
        String sourceFileContent;
        try {
            sourceFileContent = Files.readString(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + fromFileName, e);
        }
        return sourceFileContent;
    }

    private void writeReportToFile(String report, String toFileName) {
        File destinationFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destinationFile))) {
            bufferedWriter.write(createReport(report));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file " + toFileName, e);
        }
    }

    private String createReport(String sourceFileContent) {
        String[] sourceFileParts = sourceFileContent.split(",|\\R");
        StringBuilder result = new StringBuilder();
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < sourceFileParts.length; i += 2) {
            switch (sourceFileParts[i]) {
                case SUPPLY:
                    supply += Integer.parseInt(sourceFileParts[i + 1]);
                    break;
                case BUY:
                    buy += Integer.parseInt(sourceFileParts[i + 1]);
                    break;
                default:
                    break;
            }
        }
        result.append(SUPPLY + ",").append(supply).append(System.lineSeparator());
        result.append(BUY + ",").append(buy).append(System.lineSeparator());
        result.append("result,").append(supply - buy);
        return result.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || toFileName == null) {
            return;
        }
        writeReportToFile(makeReport(fromFileName), toFileName);
    }
}
