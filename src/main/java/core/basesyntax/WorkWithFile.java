package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int FIELD_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        if (isFileEmpty(toFile)) {
            try (FileWriter fileWriter = new FileWriter(toFile)) {
                fileWriter.write(prepareReport(readFile(fromFileName)));
            } catch (IOException e) {
                throw new RuntimeException("File doesn't exist " + toFileName);
            }
        } else {
            System.out.println("File isn't empty. Delete data.");
        }
    }

    public String[] readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readString(fromFile.toPath()).split("\n");
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist " + fromFileName);
        }
    }

    public String prepareReport(String[] dataArray) {
        int supplySum = 0;
        int buySum = 0;
        for (String raw : dataArray) {
            String[] temp = raw.split(",");
            if (temp[FIELD_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(temp[VALUE_INDEX]);
            } else {
                buySum += Integer.parseInt(temp[VALUE_INDEX]);
            }
        }
        return new StringBuilder()
                .append(SUPPLY).append(",").append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(",").append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(supplySum - buySum)
                .toString();
    }

    public boolean isFileEmpty(File file) {
        return file.getFreeSpace() == 0;
    }
}
