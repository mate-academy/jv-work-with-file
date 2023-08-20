package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private String typeSupply = "supply";
    private String typeBuy = "buy";
    private String typeResult = "result";
    private String specificCharacter = ",";
    private int word = 0;
    private int amount = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String dataToFile = dataProcess(dataFromFile);
        writeToFile(toFileName, dataToFile);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return stringBuilder.toString();
    }

    private String dataProcess(String toFileName) {
        String[] dataFromFile = toFileName.toString().split(" ");
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            String[] dataOnLine = dataFromFile[i].split(specificCharacter);
            if (dataOnLine[word].equals(typeSupply)) {
                amountOfSupply += Integer.parseInt(dataOnLine[amount]);
            }
            if (dataOnLine[word].equals(typeBuy)) {
                amountOfBuy += Integer.parseInt(dataOnLine[amount]);
            }
        }
        int finalAmount = amountOfSupply - amountOfBuy;
        StringBuilder result = new StringBuilder();
        result = result.append(typeSupply + specificCharacter + amountOfSupply
                        + System.lineSeparator()).append(typeBuy + specificCharacter
                        + amountOfBuy + System.lineSeparator()).append(typeResult
                        + specificCharacter + finalAmount + System.lineSeparator());
        return result.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

}

