package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SPLITTER = ",";
    private static final String WHITE_SPACE_REGEX = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = readFile(new File(fromFileName));
        writeToFile(toFileName, getReport(stringBuilder));
    }

    public StringBuilder readFile(File fileName) {
        StringBuilder allInfoFromFile = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                allInfoFromFile.append(value).append(WHITE_SPACE_REGEX);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read fileName", e);
        }
        return allInfoFromFile;
    }

    public int[] getReport(StringBuilder allInfoFromFile) {
        String[] strings = allInfoFromFile.toString().split(WHITE_SPACE_REGEX);
        int supply = 0;
        int buy = 0;

        for (String string : strings) {
            if (string.contains(SUPPLY)) {
                supply += Integer.parseInt(string.substring(7));
            } else {
                buy += Integer.parseInt(string.substring(4));
            }
        }
        return new int[] {supply, buy};
    }

    public void writeToFile(String nameFile, int[] date) {
        File file = new File(nameFile);
        String report = SUPPLY + SPLITTER + date[0] + System.lineSeparator()
                + BUY + SPLITTER + date[1] + System.lineSeparator()
                + RESULT + SPLITTER + (date[0] - date[1]);
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}

