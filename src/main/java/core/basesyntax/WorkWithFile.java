package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SEPARATED_LINE_NAME = 0;
    private static final int SEPARATED_LINE_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (
                BufferedReader fromFileNameBR = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter toFileNameBW = new BufferedWriter(new FileWriter(toFileName));
            ) {
            String line;
            int buy = 0;
            int supply = 0;
            while ((line = fromFileNameBR.readLine()) != null) {
                String[] separatedLine = line.split(",");
                String name = separatedLine[SEPARATED_LINE_NAME];
                String value = separatedLine[SEPARATED_LINE_VALUE];
                if (name.equals("buy")) {
                    buy += Integer.parseInt(value);
                } else if (name.equals("supply")) {
                    supply += Integer.parseInt(value);
                }
            }
            toFileNameBW.write(this.prepareStingFile(supply, buy));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String prepareStingFile(int supply, int buy) {
        StringBuilder sb = new StringBuilder();
        int result = supply - buy;
        return sb.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result)
                .toString();
    }
}
