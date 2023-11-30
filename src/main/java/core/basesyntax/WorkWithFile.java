package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final int INDEX_OPERATION_TYPE = 0;
    public static final int INDEX_AMOUNT = 1;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String SEPARATOR = ",";
    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readData(fromFileName);
        writeData(toFileName);
    }

    private void readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            List<String> allDataLines = Files.readAllLines(fromFile.toPath());
            for (String line : allDataLines) {
                String[] split = line.split(SEPARATOR);
                if (split[INDEX_OPERATION_TYPE].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(split[INDEX_AMOUNT]);
                }
                if (split[INDEX_OPERATION_TYPE].equals(BUY)) {
                    buyAmount += Integer.parseInt(split[INDEX_AMOUNT]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void writeData(String toFileName) {
        File toFile = new File(toFileName);
        if (!toFile.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(createReport());
            } catch (IOException e) {
                throw new RuntimeException("Can`t write file", e);
            }
        }
    }

    private String createReport() {
        return SUPPLY + SEPARATOR + supplyAmount + System.lineSeparator()
                + BUY + SEPARATOR + buyAmount + System.lineSeparator()
                + RESULT + SEPARATOR + (supplyAmount - buyAmount) + System.lineSeparator();
    }
}
