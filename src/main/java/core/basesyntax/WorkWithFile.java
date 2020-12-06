package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();

            while (value != null) {
                String[] temp = value.split(",");
                if (temp[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(temp[1]);
                } else {
                    buy += Integer.parseInt(temp[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        writeToFile(toFileName, createReport(supply, buy));

    }

    private String createReport(int supply, int buy) {
        return "supply," + supply + System.lineSeparator() + "buy,"
                + buy + System.lineSeparator() + "result," + (supply - buy);
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
