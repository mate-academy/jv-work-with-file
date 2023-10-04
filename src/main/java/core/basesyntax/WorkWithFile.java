package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] result = readFile(fromFileName);
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            for (String line : result) {
                fileWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file" + toFileName, e);
        }
    }

    public static String[] readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfPurchases = 0;
        int numberOfSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String fileStr;
            while ((fileStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(fileStr).append(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        String[] cloneBuilder = stringBuilder.toString().split(",");
        for (int i = 0; i < cloneBuilder.length; i++) {
            if (cloneBuilder[i].equalsIgnoreCase("supply")) {
                numberOfSupply += Integer.parseInt(cloneBuilder[i + 1]);
            }
            if (cloneBuilder[i].equalsIgnoreCase("buy")) {
                numberOfPurchases += Integer.parseInt(cloneBuilder[i + 1]);
            }
        }
        return new String[]{"supply," + numberOfSupply, "buy,"
                + numberOfPurchases, "result," + (numberOfSupply - numberOfPurchases)};
    }
}
