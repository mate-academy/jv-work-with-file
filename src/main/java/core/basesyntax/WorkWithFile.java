package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    String splitter = ",";

    public void getStatistic(String fromFileName, String toFileName) {

    }

    private List readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        BufferedReader reader = null;
        List<List<String>> fileData = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                String[] line = value.split(splitter);
                fileData.add(Arrays.asList(line));
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close file", e);
                }
            }
        }
        return fileData;
    }

    private StringBuilder createStatistic(List<List<String>> inputData) {
        int allSupply = 0;
        int allBuy = 0;
        for (List<String> line : inputData) {
            String[] tempData = line.toArray(new String[line.size()]);
            switch (tempData[0]) {
                case "buy":
                    allBuy += Integer.valueOf(tempData[1]);
                    break;
                case "supply":
                    allSupply += Integer.valueOf(tempData[1]);
                    break;
                default:
            }
        }
        int result = allSupply - allBuy;
        StringBuilder reportStatictic = new StringBuilder();
        reportStatictic.append("supply")
                .append(splitter)
                .append(allSupply)
                .append(System.lineSeparator())
                .append("buy")
                .append(splitter)
                .append(allBuy)
                .append(System.lineSeparator())
                .append("result")
                .append(splitter)
                .append(result);
        return reportStatictic;
    }

    private void writeToFile(StringBuilder report, String toFileName) {
        File tofile = new File(toFileName);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(tofile));
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close file");
                }
            }
        }
    }
}