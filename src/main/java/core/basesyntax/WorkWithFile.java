package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))
        ) {
            String tempStr = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;
            while (tempStr != null) {
                String[] tempStrArr = tempStr.split(",");
                int value = Integer.parseInt(tempStrArr[VALUE_INDEX]);
                if (tempStrArr[ACTION_INDEX].equals("supply")) {
                    supply += value;
                } else {
                    buy += value;
                }
                tempStr = bufferedReader.readLine();
            }
            String report = new StringBuilder("supply,")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(supply - buy)
                    .toString();
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
