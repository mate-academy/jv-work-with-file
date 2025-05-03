package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder resultOfread = read(fromFileName);
        String resultCalculate = calculate(resultOfread);
        write(toFileName, resultCalculate);
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

    private String calculate(StringBuilder stringBuilder) {
        int supply = 0;
        int buy = 0;
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
        StringBuilder bilder = new StringBuilder();
        bilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return bilder.toString();
    }

    private void write(String toFileName, String resultCalculate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultCalculate);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
