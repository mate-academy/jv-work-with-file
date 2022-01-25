package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        String[] res;
        StringBuilder builder = new StringBuilder();
        final String CommaSeperator = ",";
        int supply = 0;
        int buy = 0;
        int result = 0;
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            res = strings.toArray(new String[strings.size()]);
            for (String a: res) {
                String[] temporary = a.split(",");
                if (temporary[0].equals("supply")) {
                    supply += Integer.valueOf(temporary[1]);;
                } else if (temporary[0].equals("buy")) {
                    buy += Integer.valueOf(temporary[1]);
                }
            }
            result += supply - buy;
            builder.append("supply").append(CommaSeperator).append(supply
            ).append(System.lineSeparator()).append("buy").append(CommaSeperator
            ).append(buy).append(System.lineSeparator()
            ).append("result").append(CommaSeperator
            ).append(result).append(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }
        File fileOne = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOne, true));) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file...", e);
        }
    }
}
