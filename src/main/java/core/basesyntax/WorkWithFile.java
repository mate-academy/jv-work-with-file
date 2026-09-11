package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    public static final String CSV_SUPPLY = "supply";
    public static final String CSV_BUY = "buy";
    public static final String CSV_RESULT = "result";
    public static final String CSV_DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileContent = readFile(Paths.get(fromFileName));
        String result = getResult(fileContent);
        writeToFile(result, toFileName);
    }

    public String[] readFile(Path filePath) {
        String[] fileContent;

        try {
            fileContent = Files.readAllLines(filePath).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + filePath, e);
        }

        return fileContent;

    }

    public String getResult(String[] fileContent) {
        StringBuilder sb = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : fileContent) {
            String[] fields = line.split(CSV_DELIMITER);
            if (fields[0].equals(CSV_SUPPLY)) {
                supplyTotal += Integer.parseInt(fields[1]);
            } else {
                buyTotal += Integer.parseInt(fields[1]);
            }
        }

        sb.append(CSV_SUPPLY).append(CSV_DELIMITER).append(supplyTotal).append("\n")
                .append(CSV_BUY).append(CSV_DELIMITER).append(buyTotal).append("\n")
                .append(CSV_RESULT).append(CSV_DELIMITER).append(supplyTotal - buyTotal);

        return sb.toString();
    }

    public void writeToFile(String result, String toFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + toFileName, e);
        }
    }
}
