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
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value.toCharArray()[0] == 'b') {
                    buy += Integer.parseInt(value.substring(value.indexOf(',') + 1));
                } else {
                    supply += Integer.parseInt(value.substring(value.indexOf(',') + 1));
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("supply,");
            stringBuilder.append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file " + toFileName, e);
        }
    }
}
