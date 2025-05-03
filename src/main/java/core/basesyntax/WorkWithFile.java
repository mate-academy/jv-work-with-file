package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int STRING_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = readData(fromFileName, BUY);
        int supply = readData(fromFileName, SUPPLY);
        int result = supply - buy;
        String report = getReport(supply, buy, result);
        writeData(report, toFileName);
    }

    public int readData(String fromFileName, String info) {
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                if (info.equals(data[STRING_INDEX])) {
                    result += Integer.parseInt(data[NUMBER_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file.", e);
        }
        return result;
    }

    public String getReport(int supply, int buy, int result) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    public void writeData(String report, String toFileName) {
        File resultFile = new File(toFileName);
        try {
            Files.writeString(resultFile.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + toFileName, e);
        }
    }
}
