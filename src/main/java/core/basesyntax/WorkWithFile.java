package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        final int operation_index = 0;
        final int amount_index = 1;
        int allSupply = 0;
        int allBuy = 0;
        String resStr = readFromFile(fromFileName);
        String[] input = resStr.split(System.lineSeparator());
        for (String line : input) {
            String[] splittedLine = line.split(",");
            if (splittedLine[operation_index].equals("supply")) {
                allSupply += Integer.parseInt(splittedLine[amount_index]);
                continue;
            }
            allBuy += Integer.parseInt(splittedLine[amount_index]);
        }
        int result = allSupply - allBuy;
        writeToFile(toFileName, prepareReport(allSupply, allBuy, result));
    }

    private String readFromFile(String fromFileName) {
        final StringBuilder resultString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                resultString.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName, e);
        }
        return resultString.toString();
    }

    private String prepareReport(int allSupply, int allBuy, int result) {
        return "supply," + allSupply + System.lineSeparator() + "buy,"
                + allBuy + System.lineSeparator() + "result," + result;
    }

    private void writeToFile(String toFileName, String readyReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(readyReport);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file " + toFileName, e);
        }
    }
}
