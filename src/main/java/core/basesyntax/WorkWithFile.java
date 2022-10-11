package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int buy = 0;
        int supply = 0;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = reader.readLine();
            }
            String resultString = stringBuilder.toString();
            String[] allArray = resultString.split(",");
            for (int i = 0; i < allArray.length; i = i + 2) {
                if (allArray[i].equals("buy")) {
                    buy = buy + Integer.parseInt(allArray[i + 1]);
                }
                if (allArray[i].equals("supply")) {
                    supply = supply + Integer.parseInt(allArray[i + 1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        int result = supply - buy;
        StringBuilder resultStringBuilder = new StringBuilder("supply,")
                .append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        String resultString = resultStringBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(resultString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file" + toFileName, e);
        }
    }
}
