package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createResult(readFile(fromFileName)), toFileName);
    }

    private int[] readFile(String pathToFile) {
        int[] result = new int[2];

        File file = new File(pathToFile);
        try {
            List<String> dataFromFile = Files.readAllLines(file.toPath());
            for (String data : dataFromFile) {
                String[] supplyOrBuyData = data.split(",");
                if (supplyOrBuyData[0].equals("supply")) {
                    result[0] += Integer.parseInt(supplyOrBuyData[1]);
                } else {
                    result[1] += Integer.parseInt(supplyOrBuyData[1]);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + pathToFile, e);
        }
    }

    private String createResult(int[] values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(values[0]).append(System.lineSeparator())
                .append("buy,").append(values[1]).append(System.lineSeparator())
                .append("result,").append(values[0] - values[1]);
        return stringBuilder.toString();
    }

    private void writeToFile(String result, String pathToFile) {
        File file = new File(pathToFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create file" + pathToFile, e);
            }
        }
        try {
            Files.write(file.toPath(), result.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + pathToFile, e);
        }
    }
}
