package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private StringBuilder stringBuilder;
    private String resultString;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        int sumSupply = 0;
        int sumBuy = 0;
        int result = 0;
        String[] split = stringBuilder.toString().split("\\W+");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("supply")) {
                sumSupply += Integer.parseInt(split[i + 1]);
            }
            if (split[i].equals("buy")) {
                sumBuy += Integer.parseInt(split[i + 1]);
            }
        }

        result = sumSupply - sumBuy;
        resultString = "supply," + sumSupply + System.lineSeparator() + "buy," + sumBuy
                + System.lineSeparator() + "result," + result;
        File fileOut = new File(toFileName);
        try {
            Files.write(fileOut.toPath(), resultString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cant write file", e);
        }
    }
}
