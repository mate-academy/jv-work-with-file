package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
private static final String SUPPLY = "supply";
private static final String BUY = "buy";
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + toFileName, e);
        }
        String[] data = stringBuilder.toString().split(",");
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            int result = getSupply(data) - getBuy(data);
            bufferedWriter.write("  supply," + getSupply(data) + System.lineSeparator()
                    + "buy," + getBuy(data) + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + toFileName, e);
        }
    }

    public int getSupply(String[] data) {
        int supply = 0;
        for (int i = 0;i < data.length;i += 2) {
            if (data[i].equals(SUPPLY)) {
                supply += Integer.parseInt(data[i + 1]);
            }
        }
        return supply;
    }

    public int getBuy(String[] data) {
        int buy = 0;
        for (int i = 0;i < data.length;i += 2) {
            if (data[i].equals(BUY)) {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        return buy;
    }
}
