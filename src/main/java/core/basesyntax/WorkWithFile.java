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
        StringBuilder initialData = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(initialFile));
            String bufferValue = bufferedReader.readLine();

            while (bufferValue != null) {
                initialData.append(bufferValue).append("\n");
                bufferValue = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }

        String[] initialDataList = initialData.toString().split("\n");
        StringBuilder writerStringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result = 0;

        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(newFile))) {
            for (String initialDataItem: initialDataList) {
                String name = initialDataItem.split(",")[0];
                String count = initialDataItem.split(",")[1];

                if (name.equals("supply")) {
                    supply += Integer.parseInt(count);
                } else {
                    buy += Integer.parseInt(count);
                }
            }

            result = supply - buy;

            writerStringBuilder
                    .append("supply,")
                    .append(supply)
                    .append("\n")
                    .append("buy,")
                    .append(buy)
                    .append("\n")
                    .append("result,")
                    .append(result);

            bufferWriter.write(writerStringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
