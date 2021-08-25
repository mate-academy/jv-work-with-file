package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = readFromFile(fromFileName);
        String result = createReport(stringFromFile);
        writeToFile(toFileName, result);
    }

    private String createReport(String infoFromFile) {
        String[] information = infoFromFile.split(",");
        StringBuilder result = new StringBuilder();
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        for (int i = 0; i < information.length - 1; i += 2) {
            sumOfBuy += information[i].equals("buy")
                    ? Integer.parseInt(information[i + 1]) : 0;
            sumOfSupply += information[i].equals("supply")
                    ? Integer.parseInt(information[i + 1]) : 0;
        }
        result.append("supply,")
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(sumOfSupply - sumOfBuy);
        return result.toString();
    }

    private String readFromFile(String fileName) {
        StringBuilder stringFromFile = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringFromFile.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringFromFile.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

}
