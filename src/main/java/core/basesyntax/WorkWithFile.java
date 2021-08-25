package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName,toFileName).split(",");
        File file = new File(toFileName);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < data.length; i += 2) {
            if (data[i].equals(SUPPLY)) {
                supply += Integer.parseInt(data[i + 1]);
            } else {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        writeToFile(supply,buy,toFileName);
    }

    public String readFromFile(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + toFileName, e);
        }
    }

    public void writeToFile(int supply,int buy,String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int result = supply - buy;
            bufferedWriter.write("  supply," + supply
                    + System.lineSeparator()
                    + "buy," + buy
                    + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + toFileName, e);
        }
    }
}
