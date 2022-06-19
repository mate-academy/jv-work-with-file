package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getData(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getResult(dataFromFile));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName + e);
        }
    }

    private String getData(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str + " ");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + file, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
        return stringBuilder.toString();
    }

    private String getResult(String data) {
        int buyProduct = 0;
        int supplyProduct = 0;
        int result = 0;
        // после data.split(","), цифры от buy находятся с элементом supply
        // в одной строке(в следующем элементе), что бы извлечь число
        // использовал regex: "[^0-9]"
        String[] strings = data.split(",");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("buy")) {
                buyProduct += Integer.parseInt(strings[i + 1].replaceAll("[^0-9]", ""));
            } else if (strings[i].contains("supply")) {
                supplyProduct += Integer.parseInt(
                        strings[i + 1].replaceAll("[^0-9]", ""));
            }
        }
        result = supplyProduct - buyProduct;

        return "supply," + supplyProduct + System.lineSeparator()
                + "buy," + buyProduct + System.lineSeparator()
                + "result," + result;
    }
}
