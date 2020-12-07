package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SYMBOL = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] strings = readInformation(fromFileName);
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("supply")) {
                buy = buy + Integer.parseInt(strings[i + 1]);
            }
            if (strings[i].equals("buy")) {
                supply = supply + Integer.parseInt(strings[i + 1]);
            }
        }
        writeInformation(supply, buy, toFileName);

    }

    private String[] readInformation(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = reader.readLine();
            }
            String[] textString = stringBuilder.toString().split(",");
            return textString;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeInformation(int buy, int supply, String toFileName) {
        try (FileWriter writer = new FileWriter(new File(toFileName))) {
            StringBuilder sb = new StringBuilder();
            sb.append(SUPPLY);
            sb.append(SYMBOL);
            sb.append(Integer.toString(supply));
            sb.append(System.lineSeparator());

            sb.append(BUY);
            sb.append(SYMBOL);
            sb.append(Integer.toString(buy));
            sb.append(System.lineSeparator());

            sb.append(RESULT);
            sb.append(SYMBOL);
            sb.append(Integer.toString(supply - buy));
            sb.append(System.lineSeparator());

            writer.write(sb.toString());

        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file " + toFileName, e);
        }
    }
}
