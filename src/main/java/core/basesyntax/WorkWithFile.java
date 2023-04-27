package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final char COMMA = ',';
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        int valueAfterComma;

        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String readDataFromFile = bufferedReader.readLine();
            while (readDataFromFile != null) {
                stringBuilder.append(readDataFromFile).append(" ");
                readDataFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }

        String[] dataFromFile = stringBuilder.toString().split(" ");
        for (String data: dataFromFile) {
            valueAfterComma = Integer.parseInt(data.substring(data.indexOf(COMMA) + 1));
            if (data.startsWith(SUPPLY)) {
                sumOfSupply += valueAfterComma;
            } else {
                sumOfBuy += valueAfterComma;
            }
        }
        String[] dataWriteToFile = new String[3];
        dataWriteToFile[0] = SUPPLY + COMMA + sumOfSupply + System.lineSeparator();
        dataWriteToFile[1] = BUY + COMMA + sumOfBuy + System.lineSeparator();
        dataWriteToFile[2] = RESULT + COMMA + (sumOfSupply - sumOfBuy);

        File toFile = new File(toFileName);
        if (!toFile.exists()) {
            try {
                toFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create file: " + toFileName, e);
            }

            for (String data: dataWriteToFile) {
                try {
                    Files.write(toFile.toPath(), data.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data to file: " + toFileName, e);
                }
            }
        }
    }
}
