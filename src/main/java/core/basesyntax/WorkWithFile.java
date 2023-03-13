package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_OR_BUY = 0;
    private static final int VALUE = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String readFile = fileReader(file);
        int supply = 0;
        int buy = 0;
        String[] valueArray = readFile.split(" ");

        for (int i = 0; i < valueArray.length; i++) {
            String[] elements = valueArray[i].split(SEPARATOR);
            switch (elements[SUPPLY_OR_BUY]) {
                case ("supply"):
                    supply += Integer.valueOf(elements[VALUE]);
                    break;
                case ("buy"):
                    buy += Integer.valueOf(elements[VALUE]);
                    break;
                default:
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder()
                .append("supply").append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append("buy").append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append("result").append(SEPARATOR).append(supply - buy);

        File newFile = new File(toFileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        fileWriter(newFile, stringBuilder.toString());
    }

    private String fileReader(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void fileWriter(File file, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
