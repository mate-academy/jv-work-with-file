package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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

    public void getStatistic(String fromFileName, String toFileName) {
        generateReport(fromFileName, toFileName);
    }

    private void generateReport(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            List<String> allLines = Files.readAllLines(Paths.get(fromFileName));
            for (int i = 0; i < allLines.size(); i++) {
                String[] nameProduct = bufferedReader.readLine().split(",");
                if (nameProduct[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(nameProduct[1]);
                } else {
                    buy += Integer.parseInt(nameProduct[1]);
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
                .append(supply >= buy ? supply - buy : buy - supply);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(writeString.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + toFileName, e);
        }
    }
}
