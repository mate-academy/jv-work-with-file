package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getDataFromFile(fromFileName);

        String textToWrite = searchForIdenticalAndWrite(dataFromFile);

        writeDataToFile(textToWrite, toFileName);
    }

    public String getDataFromFile(String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder strBuild = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                strBuild.append(value).append(" ");
                value = bufferedReader.readLine();
            }

            return strBuild.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String searchForIdenticalAndWrite(String text) {
        int countOfSupply = 0;
        int countOfBuy = 0;
        StringBuilder strBuild = new StringBuilder();

        String[] textInArray = text.split(" ");
        for (int i = 0; i < textInArray.length; i++) {
            String[] strArray = textInArray[i].split(",");
            if (strArray[0].equals("supply")) {
                countOfSupply += Integer.parseInt(strArray[1]);
            } else {
                countOfBuy += Integer.parseInt(strArray[1]);
            }
        }

        int result = countOfSupply - countOfBuy;
        strBuild.append("supply,").append(countOfSupply).append(System.lineSeparator())
                .append("buy,").append(countOfBuy).append(System.lineSeparator())
                .append("result,").append(result);

        return strBuild.toString();
    }

    public void writeDataToFile(String text, String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
