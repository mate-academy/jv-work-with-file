package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String FILE_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUYING_OPERATION = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                String[] splitOperations = line.split(FILE_SEPARATOR);
                if (isSupply(splitOperations)) {
                    supply += Integer.parseInt(splitOperations[VALUE_INDEX]);
                }
                if (isBuy(splitOperations)) {
                    buy += Integer.parseInt(splitOperations[VALUE_INDEX]);
                }
                line = reader.readLine();
            }
            writeToFile(createStaticticString(supply, buy), toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
    }

    private boolean isSupply(String[] operations) {
        return operations[OPERATION_INDEX].equals(SUPPLY_OPERATION);
    }

    private boolean isBuy(String[] operations) {
        return operations[OPERATION_INDEX].equals(BUYING_OPERATION);
    }

    private void writeToFile(String info, String toFileName) {
        try {
            Files.write(Path.of(toFileName), info.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }

    private String createStaticticString(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION)
                .append(FILE_SEPARATOR)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUYING_OPERATION)
                .append(FILE_SEPARATOR)
                .append(buy)
                .append(System.lineSeparator())
                .append("result").append(FILE_SEPARATOR)
                .append(supply - buy);
        return builder.toString();
    }
}
