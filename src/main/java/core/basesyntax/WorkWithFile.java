package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String inputString = new String();
        int supply = 0;
        int buy = 0;
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        inputString = stringBuilder.toString().replace(System.lineSeparator(), ",");
        String [] strArray = inputString.split(",");
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals("buy")) {
                buy += Integer.parseInt(strArray[i + 1]);
            }
            if (strArray[i].equals("supply")) {
                supply += Integer.parseInt(strArray[i + 1]);
            }
        }
        int result = 0;
        result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFile, false));
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close BufferedWriter", e);
            }
        }
    }
}
