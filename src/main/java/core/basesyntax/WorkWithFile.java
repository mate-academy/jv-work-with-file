package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File getInfoFrom = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getInfoFrom));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int buy = 0;
        int supply = 0;
        String allData = builder.toString();
        String[] arrayWithData = allData.split(",");
        for (int i = 0; i < arrayWithData.length; i++) {
            if (arrayWithData[i].equals("buy")) {
                buy += Integer.parseInt(arrayWithData[i + 1]);
            } else if (arrayWithData[i].equals("supply")) {
                supply += Integer.parseInt(arrayWithData[i + 1]);
            }
        }
        int result = supply - buy;

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        File writeInfoTo = new File(toFileName);
        try {
            FileWriter fileWriter = new FileWriter(writeInfoTo);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(resultBuilder.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
