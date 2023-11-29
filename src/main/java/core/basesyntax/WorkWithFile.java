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
    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readData(fromFileName);
        generateReport(toFileName);
    }

    private void readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            List<String> allDataLines = Files.readAllLines(fromFile.toPath());
            for (String line : allDataLines) {
                String[] split = line.split(",");
                if (split[INDEX_OPERATION_TYPE].equals("supply")) {
                    supplyAmount += Integer.parseInt(split[1]);
                }
                if (split[0].equals("buy")) {
                    buyAmount += Integer.parseInt(split[INDEX_AMOUNT]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void generateReport(String toFileName) {
        File toFile = new File(toFileName);
        if (!toFile.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write("supply," + supplyAmount + System.lineSeparator()
                        + "buy," + buyAmount + System.lineSeparator()
                        + "result," + (supplyAmount - buyAmount) + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can`t write file", e);
            }
        }
    }
}
