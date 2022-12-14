package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        getSupply(fromFileName);
        writer(fromFileName, toFileName);
    }

    public String[] readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] valueFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String elementsInFile = reader.readLine();
            while (elementsInFile != null) {
                stringBuilder.append(elementsInFile).append(System.lineSeparator());
                elementsInFile = reader.readLine();
            }
            valueFile = stringBuilder.toString().split("\\W+");
            return valueFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public int getSupply(String fileName) {
        int supply = 0;
        String[] arraySupply = readFile(fileName);
        for (int i = 0; i < arraySupply.length; i++) {
            if (arraySupply[i].equals("supply")) {
                supply += Integer.parseInt(arraySupply[i + 1]);
            }
        }
        return supply;
    }

    public int getBuy(String fileName) {
        int buy = 0;
        String[] arrayBuy = readFile(fileName);
        for (int i = 0; i < arrayBuy.length; i++) {
            if (arrayBuy[i].equals("buy")) {
                buy += Integer.parseInt(arrayBuy[i + 1]);
            }
        }
        return buy;
    }

    public int getResult(String fileName) {
        return getSupply(fileName) - getBuy(fileName);
    }

    public void writer(String fileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder("supply,")
                .append(getSupply(fileName))
                .append(System.lineSeparator())
                .append("buy,")
                .append(getBuy(fileName))
                .append(System.lineSeparator())
                .append("result,")
                .append(getResult(fileName));
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
