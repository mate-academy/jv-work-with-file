package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final String SPLIT_CHARACTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readCsvFile(fromFileName);

        int operationSupply = 0;
        int operationBuy = 0;

        for (String line : dataFromFile) {
            String[] splitLine = line.split(SPLIT_CHARACTER);
            if (splitLine[0].equals("supply")) {
                operationSupply += Integer.parseInt(splitLine[1]);
            }
            if (splitLine[0].equals("buy")) {
                operationBuy += Integer.parseInt(splitLine[1]);
            }
        }
        int result = operationSupply - operationBuy;

        String writingData = createString(operationSupply, operationBuy, result);
        writeToFile(toFileName, writingData);
    }

    private String createString(int operationSupply, int operationBuy, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(operationSupply).append(System.lineSeparator());
        stringBuilder.append("buy,").append(operationBuy).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }

    private List readCsvFile(String pathToFile) {
        try {
            List<String> data = Files.readAllLines(Paths.get(pathToFile));
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create file", e);
            }
        }
        try {
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
