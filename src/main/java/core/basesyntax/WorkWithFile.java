package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] temp = line.split(",");
                switch (temp[0]) {
                    case "buy":
                        buy += Integer.parseInt(temp[1]);
                        break;
                    case "supply":
                        supply += Integer.parseInt(temp[1]);
                        break;
                    default:
                        System.out.println("unrecognized parameter: " + temp[0]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t read file: " + fromFileName, e);
        }
        int result = supply - buy;
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(result);
        try {
            Files.writeString(Path.of(toFileName), stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file: " + toFileName, e);
        }

    }
}
