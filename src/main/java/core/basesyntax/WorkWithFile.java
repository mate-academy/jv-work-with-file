package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFromFile(fromFileName);
        String resultReport = createReport(content);
        writeToFile(resultReport, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilderReader = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilderReader.append(System.lineSeparator()).append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Can't read file with name" + fromFileName);
        }
        return stringBuilderReader.toString();
    }

    private String createReport(String input) {
        String[] splitInput = input.split("\\W+");
        int ammountSupply = 0;
        int ammountBuy = 0;
        int result = 0;
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("supply")) {
                ammountSupply = ammountSupply + Integer.parseInt(splitInput[i + 1]);
            }
            if (splitInput[i].equals("buy")) {
                ammountBuy = ammountBuy + Integer.parseInt(splitInput[i + 1]);
            }
            result = ammountSupply - ammountBuy;
        }
        return "supply," + ammountSupply + System.lineSeparator()
                + "buy," + ammountBuy + System.lineSeparator() + "result," + result;
    }

    private void writeToFile(String report, String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file, e");
        }
    }
}


