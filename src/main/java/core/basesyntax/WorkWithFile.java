package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] dataLine = value.toLowerCase().split("\\W+");
                if (dataLine[0].equals("buy")) {
                    buy += Integer.parseInt(dataLine[1]);
                } else if (dataLine[0].equals("supply")) {
                    supply += Integer.parseInt(dataLine[1]);
                }
                value = bufferedReader.readLine();
            }
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator());
            stringBuilder.append("buy,").append(buy).append(System.lineSeparator());
            stringBuilder.append("result,").append(supply - buy);
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        file = new File(toFileName);
        try {
            Files.write(file.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
