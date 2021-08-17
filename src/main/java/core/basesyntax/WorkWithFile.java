package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
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

        String[] strings = data.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].substring(0, strings[i].indexOf(",")).equals("buy")) {
                buyProduct += Integer.parseInt(strings[i].substring((strings[i].indexOf(",")) + 1));
            } else if (strings[i].substring(0, strings[i].indexOf(",")).equals("supply")) {
                supplyProduct += Integer.parseInt(
                        strings[i].substring((strings[i].indexOf(",")) + 1));
            }
        }
        result = supplyProduct - buyProduct;

        return "supply," + supplyProduct + System.lineSeparator()
                + "buy," + buyProduct + System.lineSeparator()
                + "result," + result;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getResult(getData(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName + e);
        }
    }
}
