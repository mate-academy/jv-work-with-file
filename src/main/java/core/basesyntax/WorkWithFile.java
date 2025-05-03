package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String content = getStringFromFile(fromFileName);
        String report = takeDataForReport(content);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Wrong write file!");
        }
    }

    private String getStringFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String allWord;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((allWord = bufferedReader.readLine()) != null) {
                stringBuilder.append(allWord).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Wrong file!", e);
        }
        return String.valueOf(stringBuilder);
    }

    private String takeDataForReport(String data) {
        int counter = 0;
        int supplyInt = 0;
        int buyInt = 0;
        String[] array = data.split("\\W+");
        for (String tempString : array) {
            if (tempString.equals("supply")) {
                supplyInt += Integer.parseInt(array[counter + 1]);
            }
            if (tempString.equals("buy")) {
                buyInt += Integer.parseInt(array[counter + 1]);
            }
            counter++;
        }
        return "supply," + supplyInt + System.lineSeparator()
                + "buy," + buyInt + System.lineSeparator()
                + "result," + (supplyInt - buyInt);
    }
}
