package core.basesyntax;

import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String DILIMITER = ",";
    private static final String LINE_DILIMITER = "\n";
    private static final byte OPERATION_TYPE_POINTER = 0;
    private static final byte AMMOUNT_POINTER = 1;
    private static final String OUT_PATTERN = "supply,%d" + System.lineSeparator() + "buy,%d"
            + System.lineSeparator() + "result,%d";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmmount = 0;
        int buyAmmount = 0;
        String[] lines = readFile(fromFileName).split(LINE_DILIMITER);

        for (String line : lines) {
            String[] data = line.split(DILIMITER);
            if (data[OPERATION_TYPE_POINTER].equals(BUY)) {
                buyAmmount += Integer.parseInt(data[AMMOUNT_POINTER]);
            } else if (data[OPERATION_TYPE_POINTER].equals(SUPPLY)) {
                supplyAmmount += Integer.parseInt(data[AMMOUNT_POINTER]);
            }
        }

        writeFile(toFileName, String.format(OUT_PATTERN,
                supplyAmmount, buyAmmount, supplyAmmount - buyAmmount));
    }

    private String readFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Can`t read file: " + fileName);
        }
    }

    private void writeFile(String fileName, String data) {
        try {
            Files.writeString(Path.of(fileName), data);
        } catch (Exception e) {
            throw new RuntimeException("Can`t write file: " + fileName);
        }
    }
}
