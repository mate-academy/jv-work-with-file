package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_POS = 0;
    private static final int COST_POS = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileContent = readTheFile(fromFileName);
        System.out.println(fileContent[0]);
        int buy = 0;
        int supply = 0;
        for (String line : fileContent) {
            String[] splitLine = line.split(",");
            if (splitLine[ACTION_POS].equals("buy")) {
                buy += Integer.parseInt(splitLine[COST_POS]);
            } else {
                supply += Integer.parseInt(splitLine[COST_POS]);
            }
        }
        writeToFile(buy, supply, toFileName);
    }

    private String[] readTheFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writeToFile(int buy, int supply, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
