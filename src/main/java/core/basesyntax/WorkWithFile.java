package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> list = new ArrayList<String>();
        try {
            list = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("File cant read", e);
        }
        String[] fromFileArray = list.toArray(new String[0]);
        int counterSupply = 0;
        int counterBuy = 0;
        for (String fromFileArrayLine : fromFileArray) {
            if (fromFileArrayLine.split(",")[OPERATION_TYPE].equals("supply")) {
                counterSupply += Integer.parseInt(fromFileArrayLine.split(",")[AMOUNT]);
            } else {
                counterBuy += Integer.parseInt(fromFileArrayLine.split(",")[AMOUNT]);
            }
        }
        int result = counterSupply - counterBuy;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(counterSupply).append(System.lineSeparator())
                .append("buy,").append(counterBuy).append(System.lineSeparator())
                .append("result,").append(result);

        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException("File cant write", e);
        }
    }
}
