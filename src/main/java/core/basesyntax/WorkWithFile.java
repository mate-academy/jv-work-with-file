package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int STATEMENT_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private final StringBuilder resultString = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        int allSupply = 0;
        int allBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                resultString.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName, e);
        }
        String[] input = resultString.toString().split(System.lineSeparator());
        for (String inf : input) {
            String[] inform = inf.split(",");
            if (inform[STATEMENT_INDEX].equals("supply")) {
                allSupply += Integer.parseInt(inform[VALUE_INDEX]);
                continue;
            }
            allBuy += Integer.parseInt(inform[VALUE_INDEX]);
        }
        int result = allSupply - allBuy;
        toWriteFile(toFileName, toPrepareReport(allSupply, allBuy, result));
    }

    private String toPrepareReport(int allSupply, int allBuy, int result) {
        return "supply," + allSupply + System.lineSeparator() + "buy,"
                + allBuy + System.lineSeparator() + "result," + result;
    }

    private void toWriteFile(String toFileName, String readyReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(readyReport);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file " + toFileName, e);
        }
    }
}
