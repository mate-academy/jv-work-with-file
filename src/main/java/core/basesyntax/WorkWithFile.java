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
        int result;
        String[] operatingTypeSystem;
        StringBuilder builder = new StringBuilder();
        File myFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
            String value = reader.readLine();
            while (value != null) {
                operatingTypeSystem = value.split(",");
                if (operatingTypeSystem[0].equals("supply")) {
                    supply += Integer.parseInt(operatingTypeSystem[1]);
                } else {
                    buy += (Integer.parseInt(operatingTypeSystem[1]));
                }
                value = reader.readLine();
            }
            result = supply - buy;
            builder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(String.valueOf(builder));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + e);
        }
    }
}
