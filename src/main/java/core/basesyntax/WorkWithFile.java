package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String DELIMITER =",";
    public static final String OPERATION ="buy";
    int supplyAmount = 0;
    int buyAmount = 0;
    int result = 0;
    String operation;
    String amount;
    List<String> fileData = null;
    StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        readDataFromTheFile(fromFile);

        result = supplyAmount - buyAmount;

        stringBuilder.append("supply, " + supplyAmount +
                "buy, " + buyAmount +
                "result, " + result);

        writeToTheFile(toFile);
    }

    public void readDataFromTheFile(File fromFile) {
        try {
            fileData = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file",e);
        }

        for (String data : fileData) {
            String[] values = data.split(DELIMITER);

            operation = values[0];
            amount = values[1];

            if (operation.equals(OPERATION))
                buyAmount += Integer.parseInt(amount);
            else
                supplyAmount += Integer.parseInt(amount);
        }
    }

    public void writeToTheFile(File toFile) {
        try {
            Files.write(toFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cant write to the file",e);
        }
    }
}
