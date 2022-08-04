package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SPLITTER = ",";
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result;
        try (BufferedReader lineReader = new BufferedReader(new FileReader(fromFile))) {
            String line = lineReader.readLine();
            while (line != null) {
                if (SUPPLY_STRING.equals(line.split(LINE_SPLITTER)[0])) {
                    supply += Integer.parseInt(line.split(LINE_SPLITTER)[1]);
                } else {
                    buy += Integer.parseInt(line.split(LINE_SPLITTER)[1]);
                }
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        result = supply - buy;
        String output = getOutput(supply, buy, result).toString();
        writeToFile(toFileName, output);
    }

    private void writeToFile(String toFileName, String output) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            writer.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private StringBuilder getOutput(int supply, int buy, int result) {
        StringBuilder output = new StringBuilder();
        output.append(SUPPLY_STRING).append(LINE_SPLITTER).append(supply)
                .append(System.lineSeparator())
                .append(BUY_STRING).append(LINE_SPLITTER).append(buy)
                .append(System.lineSeparator())
                .append(RESULT_STRING).append(LINE_SPLITTER).append(result);
        return output;
    }
}
