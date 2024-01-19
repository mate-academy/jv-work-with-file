package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SEPARATOR);
                value = bufferedReader.readLine();
            }
            String[] fromFileData = stringBuilder.toString().split(SEPARATOR);
            int buyCount = 0;
            int supplyCount = 0;
            for (int i = 0; i < fromFileData.length; i++) {
                if (fromFileData[i].equals("buy")) {
                    buyCount += Integer.parseInt(fromFileData[i + 1]);
                } else if (fromFileData[i].equals("supply")) {
                    supplyCount += Integer.parseInt(fromFileData[i + 1]);
                }
            }
            StringBuilder report = new StringBuilder();
            report.append("supply,").append(supplyCount).append(System.lineSeparator())
                    .append("buy,").append(buyCount).append(System.lineSeparator())
                    .append("result,").append(supplyCount - buyCount);
            Files.write(Path.of(toFileName), report.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t create new file", e);
        }
    }
}
