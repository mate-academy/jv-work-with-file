package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NUMBER_IN_THE_ARRAY = 1;
    private static final int ARRAY_IN_THE_ARRAY = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                fileData.append(str).append(System.lineSeparator());
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileData.toString();
    }

    private String createReport(String data) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] arrStr = line.split(",");
            if (arrStr[ARRAY_IN_THE_ARRAY].equals("supply")) {
                sumSupply += Integer.parseInt(arrStr[NUMBER_IN_THE_ARRAY]);
            } else if (arrStr[ARRAY_IN_THE_ARRAY].equals("buy")) {
                sumBuy += Integer.parseInt(arrStr[NUMBER_IN_THE_ARRAY]);
            }
        }
        int result = sumSupply - sumBuy;
        return "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
