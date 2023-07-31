package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int MAX_SUPPLY = 500;
    private static final String INDEX_SPLIT_LINE = ",";
    private static final String INDEX_LINESEPARATOR = System.lineSeparator();
    private static String fromFileName;
    private static String toFileName;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile.fromFileName = fromFileName;
        WorkWithFile.toFileName = toFileName;
        writeNewText();
    }

    protected String[] readText() {
        File fromFile = new File(fromFileName);
        try {
            BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFile));
            StringBuilder readedFromFile = new StringBuilder();
            int data = fromFileReader.read();
            while (data != -1) {
                readedFromFile.append((char) data);
                data = fromFileReader.read();
            }
            return readedFromFile.toString().split(INDEX_LINESEPARATOR);
        } catch (Exception e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    protected StringBuilder createNewText() {
        int buy = 0;
        int supply = 0;
        for (String line : readText()) {
            if (line.split(INDEX_SPLIT_LINE)[0].equals("buy")) {
                buy += Integer.parseInt(line.split(INDEX_SPLIT_LINE)[1]);
            } else {
                supply += Integer.parseInt(line.split(INDEX_SPLIT_LINE)[1]);
            }
        }
        if (supply > MAX_SUPPLY) {
            supply = supply / 2;
            buy = buy / 2;
        }
        return new StringBuilder().append("supply,").append(supply).append(INDEX_LINESEPARATOR)
                .append("buy,").append(buy).append(INDEX_LINESEPARATOR).append("result,")
                .append(supply - buy);
    }

    protected void writeNewText() {
        File toFile = new File(toFileName);
        try {
            BufferedWriter writeTo = new BufferedWriter(new FileWriter(toFile));
            writeTo.write(createNewText().toString());
            writeTo.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}
