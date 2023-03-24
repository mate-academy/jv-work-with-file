package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private StringBuilder stringBuilder;
    private String resultString;

    private void readDataFromFile(String nameFile) {
        File file = new File(nameFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
    }

    private void processData() {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] split = stringBuilder.toString().split("\\W+");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("supply")) {
                sumSupply += Integer.parseInt(split[i + 1]);
            }
            if (split[i].equals("buy")) {
                sumBuy += Integer.parseInt(split[i + 1]);
            }
        }
        resultString = "supply," + sumSupply + System.lineSeparator() + "buy," + sumBuy
                + System.lineSeparator() + "result," + (sumSupply - sumBuy);
    }

    private void writeToFile(String toFile) {
        File fileOut = new File(toFile);
        try {
            Files.write(fileOut.toPath(), resultString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cant write file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName);
        processData();
        writeToFile(toFileName);
    }

}
