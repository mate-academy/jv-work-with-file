package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getDataFromFile(fromFileName);
        String textToWrite = dataResearch(dataFromFile);
        writeDataToFile(textToWrite, toFileName);
    }

    public String getDataFromFile(String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = bufferedReader.readLine();
            }

            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String dataResearch(String text) {
        int countOfSupply = 0;
        int countOfBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();

        String[] splitStr = text.split("\\s+");
        for (int i = 0; i < splitStr.length; i++) {
            String[] strArray = splitStr[i].split(",");
            if (strArray[0].equals("supply")) {
                countOfSupply += Integer.parseInt(strArray[1]);
            } else {
                countOfBuy += Integer.parseInt(strArray[1]);
            }
        }

        int result = countOfSupply - countOfBuy;
        stringBuilder.append("supply,").append(countOfSupply).append(System.lineSeparator())
                .append("buy,").append(countOfBuy).append(System.lineSeparator())
                .append("result,").append(result);

        return stringBuilder.toString();
    }

    public void writeDataToFile(String text, String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
