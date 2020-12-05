package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(readFile(fromFileName), toFileName);
    }

    private int[] readFile(String pathToFile) {
        int[] result = new int[2];

        File file = new File(pathToFile);
        try {
            List<String> stringList = Files.readAllLines(file.toPath());
            for (String string : stringList) {
                String[] buffer = string.split(",");
                if (buffer[0].contains("supply")) {
                    result[0] += Integer.parseInt(buffer[1]);
                } else {
                    result[1] += Integer.parseInt(buffer[1]);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeFile(int[] values, String pathToFile) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(values[0]).append(System.lineSeparator())
                .append("buy,").append(values[1]).append(System.lineSeparator())
                .append("result,").append(values[0] - values[1]);
        File file = new File(pathToFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create file", e);
            }
        }
        try {
            Files.write(file.toPath(), stringBuilder.toString().getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
