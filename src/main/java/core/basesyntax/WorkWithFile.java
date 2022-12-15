package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName).replaceAll("[\\[|\\]]","");
        String report = createReport(data);
        writeData(report, toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read it!", e);
        }
        String arraylist = strings.toString();
        return arraylist;
    }

    private static String createReport(String data) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splittedLines = data.split(" ");
        for (String line : splittedLines) {
            splittedLines = line.split(",");
            if (splittedLines[OPERATION_TYPE_INDEX].equals("supply")) {
                sumSupply += Integer.parseInt(splittedLines[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(splittedLines[AMOUNT_INDEX]);
            }
        }
        return "supply," + sumSupply
                + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
               + "result," + (sumSupply - sumBuy);
    }

    private void writeData(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
