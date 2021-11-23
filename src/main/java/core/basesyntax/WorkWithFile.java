package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static String supply = "supply";
    private static String buy = "buy";

    public void getStatistic(String fromFileName, String toFileName) {

        String string = readFromFile(fromFileName);
        int sumSupply = sumator(string, supply);
        int sumBuy = sumator(string, buy);
        String report = report(supply, sumSupply, buy, sumBuy);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        List<String> listFromFileName;
        try {
            listFromFileName = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String readString = listFromFileName.toString();
        return readString.substring(1, readString.length() - 1);
    }

    private int sumator(String strings, String article) {
        int sumArticle = 0;
        String[] splitOneStringResult = strings.split(" ");
        for (String splitOneStrRes : splitOneStringResult) {
            String[] splitTwoStrRes = splitOneStrRes.split(",");
            if (splitTwoStrRes[0].equals(article)) {
                sumArticle += Integer.parseInt(splitTwoStrRes[1]);
            }
        }
        return sumArticle;
    }

    private String report(String valueOne, int sumValueOne, String valueTwo, int sumValueTwo) {
        StringBuilder string = new StringBuilder();
        string.append(valueOne).append(",").append(sumValueOne).append(System.lineSeparator())
                .append(valueTwo).append(",").append(sumValueTwo).append(System.lineSeparator())
                .append("result,").append(sumValueOne - sumValueTwo);
        return string.toString();
    }

    private void writeToFile(String fileName, String string) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
