package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] stuffFromFile = readFromFile(fromFileName);
        String[] report = makeReport(stuffFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open file " + fileName, e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writeToFile(String nameFile, String[] report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nameFile))) {
            for (String s : report) {
                bufferedWriter.write(s);
                bufferedWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open file " + nameFile, e);
        }
    }

    private String[] makeReport(String[] stuff) {
        String[] report;
        String buy = "buy";
        String supply = "supply";
        String result = "result";
        int buySum = 0;
        int supplySum = 0;
        int tempSum = 0;
        for (String temp : stuff) {
            tempSum = Integer.parseInt(temp.substring(temp.indexOf(",") + 1));
            if (temp.substring(0, temp.indexOf(",")).equals(buy)) {
                buySum += tempSum;
            }
            if (temp.substring(0, temp.indexOf(",")).equals(supply)) {
                supplySum += tempSum;
            }
        }
        return report = new String[]{supply + "," + supplySum,
                buy + "," + buySum,
                result + "," + (supplySum - buySum)};
    }
}
