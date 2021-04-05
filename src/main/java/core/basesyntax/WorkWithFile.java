package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final static String BUY = "buy";
    private final static String SUPPLY = "supply";
    private final static String COMA = ",";
    private final static String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String[] array = readFile(file);
        int buy = 0;
        int supply = 0;

        for (String line : array) {
            String[] temp = line.split(COMA);
            if (temp[0].equals(BUY)) {
                buy += Integer.parseInt(temp[1]);
            } else {
                supply += Integer.parseInt(temp[1]);
            }
        }

        writeToFile(toFileName, buy, supply);
    }

    private String[] readFile(File file) {
        StringBuilder wordsArray = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                wordsArray.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return wordsArray.toString().split(" ");
    }

    private void writeToFile(String toFileName, int buy, int supply) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(SUPPLY + COMA + supply + System.lineSeparator());
            fileWriter.write(BUY + COMA + buy + System.lineSeparator());
            fileWriter.write(RESULT + COMA + (supply - buy));
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
    }

}
