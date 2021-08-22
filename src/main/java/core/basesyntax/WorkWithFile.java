package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        char separator = ',';
        String[] columns;
        int firstColumn = 0;
        int secondColumn = 1;
        int supply = 0;
        int buy = 0;
        int result = 0;
        try {
            String[] in = Files.newBufferedReader(Paths.get(fromFileName))
                        .lines()
                        .collect(Collectors.joining(" "))
                        .split(" ");
            for (String s : in) {
                columns = s.split(",");
                if (columns[firstColumn].equals("supply")) {
                    supply += Integer.parseInt(columns[secondColumn]);
                    result += Integer.parseInt(columns[secondColumn]);
                }
                if (columns[firstColumn].equals("buy")) {
                    buy += Integer.parseInt(columns[secondColumn]);
                    result -= Integer.parseInt(columns[secondColumn]);
                }
            }
            stringBuilder.append("supply")
                        .append(separator)
                        .append(supply)
                        .append(System.lineSeparator())
                        .append("buy")
                        .append(separator)
                        .append(buy)
                        .append(System.lineSeparator())
                        .append("result")
                        .append(separator)
                        .append(result);
            Files.write(Paths.get(toFileName), stringBuilder.toString().getBytes());

        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
    }
}
