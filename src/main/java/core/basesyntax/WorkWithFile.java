package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workFile = new WorkWithFile();
        StringBuilder resultOfread = workFile.read(fromFileName);
        workFile.calculate(resultOfread);
        workFile.write(toFileName);
    }

    private StringBuilder read(String fromFileName) {
        File readFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder;
    }

    private void calculate(StringBuilder stringBuilder) {
        String[] strings = stringBuilder.toString().split(System.lineSeparator());
        String[] arraySpliter = new String[2];
        for (String string : strings) {
            arraySpliter = string.split(",");
            if (arraySpliter[0].equals("supply")) {
                supply += Integer.parseInt(arraySpliter[1]);
            } else {
                buy += Integer.parseInt(arraySpliter[1]);
            }
        }
    }

    private void write(String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
