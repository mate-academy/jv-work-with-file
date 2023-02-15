package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private final String supplyString = "supply";
    private final String buyString = "buy";
    private final String resultString = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String resultat = createReport(fromFileName);
            writeToFile(toFileName,resultat);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String[] readFromFile(String fromFileName) throws IOException {
        String[] lines = String.join(",", Files
        .readAllLines(Paths.get(fromFileName), StandardCharsets.UTF_8)).split(",");
        return lines;
    }

    public String createReport(String fromFileName) throws IOException {
        String[] lines = readFromFile(fromFileName);
        int result = 0;
        int supply = 0;
        int buy = 0;
        for (int i = 0;i < lines.length; i++) {
            if (lines[i].equals(supplyString)) {
                supply += Integer.parseInt(lines[i + 1]);
            }
            if (lines[i].equals(buyString)) {
                buy += Integer.parseInt(lines[i + 1]);
            }
        }
        result = supply - buy;
        StringBuilder resultat = new StringBuilder();
        resultat.append(supplyString).append(",").append(supply)
                .append(System.lineSeparator()).append(buyString).append(",").append(buy)
                .append(System.lineSeparator()).append(resultString).append(",").append(result);
        return resultat.toString();
    }

    public void writeToFile(String toFileName, String resultat) throws IOException {
        File resultFile = new File(toFileName);
        resultFile.createNewFile();
        Files.writeString(Paths.get(resultFile.toURI()), resultat, StandardOpenOption.WRITE);
    }
}


