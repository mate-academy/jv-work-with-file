package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataArray = readFile(fromFileName);
        String calculations = calculate(dataArray);
        writeFile(toFileName, calculations);
    }

    public String[] readFile(String fromFileName) {
        Path fromFilePath = Paths.get(fromFileName);
        String[] dataArray;
        try {
            byte[] bytes = Files.readAllBytes(fromFilePath);
            dataArray = new String(bytes).split("\\r?\\n");
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file ", e);
        }
        return dataArray;
    }

    public String calculate(String[] dataArray) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] dataFromLine;
        for (String line : dataArray) {
            dataFromLine = line.split(",");
            if (dataFromLine[0].equals(SUPPLY)) {
                supply += Integer.parseInt(dataFromLine[1]);
            } else {
                buy += Integer.parseInt(dataFromLine[1]);
            }
        }
        result = supply - buy;
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result + System.lineSeparator();
    }

    public void writeFile(String toFileName, String calculations) {
        Path toFilePath = Paths.get(toFileName);
        try {
            Files.writeString(toFilePath,calculations);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
