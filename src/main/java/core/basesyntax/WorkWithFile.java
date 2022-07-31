package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = getData(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getResult(infoFromFile));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName + e);
        }
    }

    private String getData(String file) {
        StringBuilder reportBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                reportBuilder.append(line + " ");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + file, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
        return reportBuilder.toString();
    }

    private String getResult(String data) {
        int resultSupple = 0;
        int resultBuy = 0;
        int resultTotal = 0;
        String[] strings = data.split(",");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("buy")) {
                resultBuy += Integer.parseInt(strings[i + 1].replaceAll("[^0-9]", ""));
            } else if (strings[i].contains("supply")) {
                resultSupple += Integer.parseInt(
                        strings[i + 1].replaceAll("[^0-9]", ""));
            }
        }
        resultTotal = resultSupple - resultBuy;
        return "supply," + resultSupple + System.lineSeparator()
                + "buy," + resultBuy + System.lineSeparator()
                + "result," + resultTotal;
    }
}
