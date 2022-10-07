package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String value = reader.readLine();

            while (value != null) {
                builder.append(value)
                        .append("_");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] data = builder.toString().split("_");
        int supply = 0;
        int buy = 0;

        for (String datum : data) {
            String[] arr = datum.split(",");

            if (arr[0].equals("supply")) {
                supply += Integer.parseInt(arr[1]);
            } else {
                buy += Integer.parseInt(arr[1]);
            }
        }

        int result = supply - buy;
        String suppleInfo = "supply," + supply;
        String buyInfo = "buy," + buy;
        String resultInfo = "result," + result;
        String[] dataToWrite = new String[]{suppleInfo, buyInfo, resultInfo};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String writeDatum : dataToWrite) {
                writer.write(writeDatum);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
