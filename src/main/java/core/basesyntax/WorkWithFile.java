package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] oneLine;
        int supply = 0;
        int buy = 0;
        int result = 0;

        try {
            String[] dataFromFile = Files.newBufferedReader(Paths.get(fromFileName))
                    .lines()
                    .collect(Collectors.joining(" "))
                    .split(" ");
            for (String string : dataFromFile) {
                oneLine = string.split(",");
                Integer integer = Integer.parseInt(oneLine[1]);
                if (oneLine[0].equals("supply")) {
                    supply += integer;
                }
                if (oneLine[0].equals("buy")) {
                    buy += integer;
                }
                result = supply - buy;
            }
            stringBuilder.append("supply")
                    .append(",")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append("buy")
                    .append(",")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(",")
                    .append(result);

            Files.write(Paths.get(toFileName), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
