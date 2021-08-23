package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_OR_BUY = 0;
    private static final int SUPPLY_OR_BUY_VALUE = 1;
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeInFile(toFileName);
    }

    public void readFile(String fromFileName) {
        File readFile = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readFile));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append("-");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }

    public void writeInFile(String toFileName) {
        File writeFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
            writeFile.createNewFile();
            writer.write(calculateStatistic());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file");
        }
    }

    public String calculateStatistic() {
        String[] splitTextToLines = stringBuilder.toString().split("-");
        String[] splitedLine;
        int supply = 0;
        int buy = 0;
        for (String splitTextToLine : splitTextToLines) {
            splitedLine = splitTextToLine.split(",");
            if ("supply".equals(splitedLine[SUPPLY_OR_BUY])) {
                supply += Integer.parseInt(splitedLine[SUPPLY_OR_BUY_VALUE]);
            } else {
                buy += Integer.parseInt(splitedLine[SUPPLY_OR_BUY_VALUE]);
            }
        }
        int remain = supply - buy;
        String result = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + remain ;
        return result;
    }
}
