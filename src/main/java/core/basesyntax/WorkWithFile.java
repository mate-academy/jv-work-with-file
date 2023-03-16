package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String content = readFile(file);
        writeToFile(toFileName, getResult(content));
    }

    private String readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + file.getName(), e);
        }
    }

    private String getResult(String text) {
        int supply = 0;
        int buy = 0;
        String[] valueArray = text.split(System.lineSeparator());

        for (int i = 0; i < valueArray.length; i++) {
            String[] elements = valueArray[i].split(SEPARATOR);
            switch (elements[OPERATION_INDEX]) {
                case ("supply"):
                    supply += Integer.valueOf(elements[AMOUNT_INDEX]);
                    break;
                case ("buy"):
                    buy += Integer.valueOf(elements[AMOUNT_INDEX]);
                    break;
                default:
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder()
                .append("supply").append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append("buy").append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append("result").append(SEPARATOR).append(supply - buy);

        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String text) {
        File newFile = new File(fileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file " + fileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + fileName, e);
        }
    }
}
