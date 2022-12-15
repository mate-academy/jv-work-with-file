package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMETER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(new File(fromFileName));
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException ex) {
            throw new RuntimeException("Can't read file", ex);
        }
    }

    private String createReport(List<String> dataFromFile) {
        int supply = 0;
        int buy = 0;
        for (String line : dataFromFile) {
            int positionValue = Integer.parseInt(line.substring(line.indexOf(',') + 1));
            if (line.substring(0, line.indexOf(',')).equals(SUPPLY)) {
                supply += positionValue;
            }
            if (line.substring(0, line.indexOf(',')).equals(BUY)) {
                buy += positionValue;
            }
        }
        int result = supply - buy;
        return SUPPLY + DELIMETER + supply + System.lineSeparator()
                + BUY + DELIMETER + buy + System.lineSeparator()
                + RESULT + DELIMETER + result;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file:" + toFileName, e);
        }
    }
}
