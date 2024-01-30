package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        int supply = 0;
        int buy = 0;

        String[] stringSplit = readFile(fromFileName).split(System.lineSeparator());
        for (String entryLine : stringSplit) {
            String[] entry = entryLine.split(",");
            if (entry[0].equals("supply")) {
                supply += Integer.parseInt(entry[1]);
            }
            if (entry[0].equals("buy")) {
                buy += Integer.parseInt(entry[1]);
            }
        }

        int result = supply - buy;

        writeFile(toFileName, supply, buy, result);

    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return stringBuilder.toString();

    }

    private void writeFile(String toFileName, int supply, int buy, int result) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {

            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }

    }
}
