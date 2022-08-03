package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result;
        String lineSplitter = ",";
        String supplyString = "supply";
        try (BufferedReader lineReader = new BufferedReader(new FileReader(fromFile))) {
            String line = lineReader.readLine();
            while (line != null) {
                if (supplyString.equals(line.split(lineSplitter)[0])) {
                    supply += Integer.parseInt(line.split(lineSplitter)[1]);
                } else {
                    buy += Integer.parseInt(line.split(lineSplitter)[1]);
                }
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        result = supply - buy;
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            writer.write(getOutput(supply, buy, result).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private StringBuilder getOutput(int supply, int buy, int result) {
        StringBuilder output = new StringBuilder();
        output.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        return output;
    }
}
