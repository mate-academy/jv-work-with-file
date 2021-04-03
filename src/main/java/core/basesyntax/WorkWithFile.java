package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String[] array = readFile(file);
        int buy = 0;
        int supply = 0;

        for (String line : array) {
            String[] temp = line.split(",");
            if (temp[0].equals("buy")) {
                buy += Integer.parseInt(temp[1]);
            } else {
                supply += Integer.parseInt(temp[1]);
            }
        }

        writeToFile(toFileName, buy, supply);
    }

    private String[] readFile(File file) {
        StringBuilder wordsArray = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                wordsArray.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }
        return wordsArray.toString().split(" ");
    }

    private void writeToFile(String toFileName, int buy, int supply) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write("supply," + supply + System.lineSeparator());
            fileWriter.write("buy," + buy + System.lineSeparator());
            fileWriter.write("result," + (supply - buy));
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
    }

}
