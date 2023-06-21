package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] strings = readFile(fromFileName);

        for (String string : strings) {
            String[] splitString = string.split(SPLIT_REGEX);
            if (splitString[0].equals("supply")) {
                totalSupply += Integer.parseInt(splitString[1]);
            }
            if (splitString[0].equals("buy")) {
                totalBuy += Integer.parseInt(splitString[1]);
            }
        }
        writeFile(toFileName, totalSupply, totalBuy);
    }

    private String[] readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeFile(String toFileName, int totalSupply, int totalBuy) {
        File toFile = new File(toFileName);
        int result = totalSupply - totalBuy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            toFile.createNewFile();
            bufferedWriter.write("supply," + totalSupply + System.lineSeparator()
                    + "buy," + totalBuy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
