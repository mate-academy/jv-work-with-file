package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public static final int INDEX_OPERATION_TYPE = 0;
    public static final int INDEX_AMOUNT = 1;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String SEPARATOR = ",";
    private List<String> data = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        data = readData(fromFileName);
        createReport(data);
        writeData(toFileName);
    }

    private List<String> readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String createReport(List<String> dataForReport) {
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String line : dataForReport) {
            String[] split = line.split(SEPARATOR);
            if (split[INDEX_OPERATION_TYPE].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(split[INDEX_AMOUNT]);
            }
            if (split[INDEX_OPERATION_TYPE].equals(BUY)) {
                buyAmount += Integer.parseInt(split[INDEX_AMOUNT]);
            }
        }
        return SUPPLY + SEPARATOR + supplyAmount + System.lineSeparator()
                + BUY + SEPARATOR + buyAmount + System.lineSeparator()
                + RESULT + SEPARATOR + (supplyAmount - buyAmount) + System.lineSeparator();
    }

    private void writeData(String toFileName) {
        File toFile = new File(toFileName);
        if (!toFile.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(createReport(data));
            } catch (IOException e) {
                throw new RuntimeException("Can't write report to the file " + toFileName, e);
            }
        }
    }
}
