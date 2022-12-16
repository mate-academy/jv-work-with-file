package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";
    private static final int DATA_INDEX = 1;
    private static final int NAME_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String result = getReportData(readData(fromFileName));
        File file = new File(toFileName);
        writeDataToFile(file, result);
    }

    private String readData(String fromFileName) {
        try {
            return Files.readString(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
    }

    public String getReportData(String dataFromFile) {
        int supplyData = 0;
        int buyData = 0;
        String[] separatedData = dataFromFile.split("\n");
        for (String separatedDatum : separatedData) {
            String[] line = separatedDatum.split(",");
            if (line[NAME_INDEX].equals(NAME_SUPPLY)) {
                supplyData += Integer.parseInt(line[DATA_INDEX]);
            } else if (line[NAME_INDEX].equals(NAME_BUY)) {
                buyData += Integer.parseInt(line[DATA_INDEX]);
            }
        }
        return String.format("supply,%s%sbuy,%s%sresult,%s",
                supplyData, System.lineSeparator(),
                buyData, System.lineSeparator(), (supplyData - buyData));
    }

    private void writeDataToFile(File file, String result) {
        try {
            Files.writeString(file.toPath(), result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + file.getName(), e);
        }
    }
}
