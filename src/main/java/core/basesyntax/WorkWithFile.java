package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    protected void getStatistic(String fromFileName, String toFileName) {
        String[] fileInfo = this.readFromFile(fromFileName);
        int supplySum = 0;
        int buySum = 0;
        for (String s : fileInfo) {
            String[] stringInfo = s.split(",");
            if (stringInfo[0].equals("supply")) {
                supplySum += Integer.parseInt(stringInfo[1]);
            } else {
                buySum += Integer.parseInt(stringInfo[1]);
            }
        }
        this.writeToFile(toFileName, supplySum, buySum);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File fromFile = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(fromFile));
            int fromText = bufferedReader.read();
            while (fromText != -1) {
                stringBuilder.append((char) fromText);
                fromText = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString()
                .replace(System.lineSeparator(), " ")
                .split(" ");
    }

    private void writeToFile(String toFileName, int supplySum, int buySum) {
        String toFileInfo = "supply," + supplySum + System.lineSeparator() + "buy,"
                + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum) + System.lineSeparator();
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(toFileInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data toFile file", e);
        }
    }
}
