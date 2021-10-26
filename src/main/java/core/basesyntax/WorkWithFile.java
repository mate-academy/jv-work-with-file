package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String message = readStatistic(fromFileName);
        writeStatistic(toFileName, message);
    }

    private String readStatistic(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder letter = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            int value = reader.read();
            while (value != -1) {
                letter.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] split = letter.toString().split("\r\n");
        int supply = 0;
        int buy = 0;
        for (String value : split) {
            if (value.contains(SUPPLY)) {
                int index = value.indexOf(',') + 1;
                int length = value.length();
                supply += Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));
            } else if (value.contains(BUY)) {
                int index = value.indexOf(',') + 1;
                int length = value.length();
                buy += Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));
            }
        }
        StringBuilder resultLetter = new StringBuilder();
        resultLetter.append(SUPPLY).append(",").append(supply).append(System.lineSeparator()).append(BUY).append(",")
                .append(buy).append(System.lineSeparator()).append(RESULT).append(",").append(supply - buy);
        return resultLetter.toString();
    }

    private void writeStatistic(String toFileName, String message) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
