package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File newFile = new File(toFileName);
        File initialFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result;

        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(newFile));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(initialFile))) {
            String bufferValue = bufferedReader.readLine();
            StringBuilder writerStringBuilder = new StringBuilder();
            String name = "";
            String count = "";

            while (bufferValue != null) {
                name = bufferValue.split(",")[0];
                count = bufferValue.split(",")[1];

                if (name.equals("supply")) {
                    supply += Integer.parseInt(count);
                } else if (name.equals("buy")) {
                    buy += Integer.parseInt(count);
                }

                bufferValue = bufferedReader.readLine();
            }

            result = supply - buy;

            writerStringBuilder.append("supply,").append(supply).append("\n")
                    .append("buy,").append(buy).append("\n")
                    .append("result,").append(result);

            bufferWriter.write(writerStringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with file", e);
        }
    }
}
