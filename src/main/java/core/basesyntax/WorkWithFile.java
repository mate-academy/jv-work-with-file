package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private String createReport(int supply, int buy, int result) {
        return "supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result," + result;
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }

    private void writeToFile(String report, String fileName) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close the file", e);
                }
            }
        }
    }

    private int getNumberFromString(String string) {
        StringBuilder tempNumberString = new StringBuilder("");
        char[] charArray = string.split(",")[1].toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            tempNumberString.append(charArray[i]);
        }
        int tempNumber = Integer.parseInt(tempNumberString.toString());
        return tempNumber;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int sumBuy = 0;
        int sumSupply = 0;
        int result;
        String[] strings = readFromFile(fromFileName).split("\\n");
        for (String string: strings) {
            if (string.split(",")[0].equals("buy")) {
                sumBuy += getNumberFromString(string);
            } else {
                sumSupply += getNumberFromString(string);
            }
        }
        result = sumSupply - sumBuy;
        String report = createReport(sumSupply, sumBuy, result);
        writeToFile(report, toFileName);

    }
}
