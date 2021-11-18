package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SPLIT_CHARACTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> dataFromFile = readCsvFile(fromFileName);

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

    private ArrayList readCsvFile(String pathToFile) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            String value = reader.readLine();
            while (value != null) {
                lines.add(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return lines;
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
