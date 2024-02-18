package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);

        String[] infoArr = readFromFile(readFile).toString().split("[\\r\\n\\s]+");

        int buy = 0;
        int supply = 0;
        String buyPrefix = "buy,";
        String supplyPrefix = "supply,";
        int buyIndex;
        int supplyIndex;

        for (String info : infoArr) {

            if (info.startsWith(buyPrefix)) {
                buyIndex = Integer.parseInt(info.substring(buyPrefix.length()));
                buy += buyIndex;
            } else if (info.startsWith(supplyPrefix)) {
                supplyIndex = Integer.parseInt(info.substring(supplyPrefix.length()));
                supply += supplyIndex;
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                .append(supply - buy);

        writeToFile(reportBuilder, writeFile);
    }

    private StringBuilder readFromFile(File readFile) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read the file", e);
        }
        return builder;
    }

    private void writeToFile(StringBuilder reportBuilder, File writeFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
            writer.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }
}
