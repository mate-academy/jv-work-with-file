package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final int SIZE_LINE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            createStatistic(readFromFile(fromFileName),toFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createStatistic(List<String> lines, String toFileName) throws IOException {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : lines) {
            String[] column = line.split(",");
            if (column.length != SIZE_LINE) {
                throw new RuntimeException("Incorrect column count in '" + line + "'");
            }
            switch (column[OPERATION_TYPE]) {
                case "supply": {
                    supplyAmount += Integer.parseInt(column[AMOUNT]);
                    break;
                }
                case "buy": {
                    buyAmount += Integer.parseInt(column[AMOUNT]);
                    break;
                }
                default:
                    throw new RuntimeException("Incorrect title '"
                            + column[OPERATION_TYPE] + "' in '" + line + "'");
            }
        }
        saveStatistic(supplyAmount, buyAmount, toFileName);
    }

    private void saveStatistic(int supplyAmount, int buyAmount, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply" + "," + supplyAmount + System.lineSeparator());
            writer.write("buy" + "," + buyAmount + System.lineSeparator());
            writer.write("result" + "," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write file" + toFileName, e);
        }
    }
}
