package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int CELL_FIRST = 0;
    private static final int CELL_SECOND = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        generateReport(fromFileName, toFileName);
    }

    private void generateReport(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            List<String> allLines = Files.readAllLines(Paths.get(fromFileName));
            for (int i = 0; i < allLines.size(); i++) {
                String[] nameProduct = allLines.get(i).split(COMMA);
                if (nameProduct[CELL_FIRST].equals(SUPPLY)) {
                    supply += Integer.parseInt(nameProduct[CELL_SECOND]);
                } else {
                    buy += Integer.parseInt(nameProduct[CELL_SECOND]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }

        writeToFile(toFileName, supply, buy);
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        StringBuilder writeString = new StringBuilder();

        writeString.append(SUPPLY).append(COMMA)
                .append(supply).append(System.lineSeparator());
        writeString.append(BUY).append(COMMA)
                .append(buy).append(System.lineSeparator());
        writeString.append(RESULT).append(COMMA)
                .append(supply - buy);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(writeString.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + toFileName, e);
        }
    }
}
