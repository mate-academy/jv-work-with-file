package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String supply = "supply";
    private final String buy = "buy";
    private final String result = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = readDataFromFile(fromFile);
        String[] arrayString = stringBuilder.toString().split("/");
        int supply = 0;
        int buy = 0;
        for (String string : arrayString) {
            String[] str = string.split(",");
            if (str[0].equals(this.supply)) {
                supply += Integer.parseInt(str[1]);
            } else if (str[0].equals(this.buy)) {
                buy += Integer.parseInt(str[1]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilderOut = createOutputString(supply, buy, result);
        writeDataToFile(toFileName, stringBuilderOut);
    }

    private StringBuilder readDataFromFile(File fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append("/");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read line from file: " + fromFile, e);
        }
        return stringBuilder;
    }

    private StringBuilder createOutputString(int supply, int buy, int result) {
        StringBuilder stringBuilderOut = new StringBuilder();
        stringBuilderOut.append(this.supply).append(",").append(supply)
                .append(System.lineSeparator());
        stringBuilderOut.append(this.buy).append(",").append(buy)
                .append(System.lineSeparator());
        stringBuilderOut.append(this.result).append(",").append(result)
                .append(System.lineSeparator());
        return stringBuilderOut;
    }

    private void writeDataToFile(String toFileName, StringBuilder stringBuilderOut) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(stringBuilderOut.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write date to file: " + toFileName, e);
        }
    }

    @Override
    public String toString() {
        return "WorkWithFile{}";
    }
}
