package core.basesyntax;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_WORD = "result";
    private static final String COMMA_SEPARATOR = ",";
    private static final String REGEX = "[^\\d+$]";

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> lines = new ArrayList<>();
        File myFile = new File(fromFileName);
        Scanner scanner;
        try (FileReader filereader = new FileReader(myFile)) {
            scanner = new Scanner(filereader);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
        getResult(lines, toFileName);
    }

    private void getResult(ArrayList<String> lines, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        int total;

        for (String line : lines) {
            if (line.contains(SUPPLY_OPERATION)) {
                sumSupply += Integer.parseInt(line.replaceAll(REGEX, ""));
            }
            if (line.contains(BUY_OPERATION)) {
                sumBuy += Integer.parseInt(line.replaceAll(REGEX, ""));
            }
        }
        total = sumSupply - sumBuy;
        getReport(sumSupply, sumBuy, total, toFileName);
    }

    private void getReport(int sumSupply, int sumBuy, int total, String toFileName) {
        String result = "";
        StringBuilder builder = new StringBuilder(result);
        builder.append(SUPPLY_OPERATION).append(COMMA_SEPARATOR)
                .append(sumSupply).append(System.lineSeparator());
        builder.append(BUY_OPERATION).append(COMMA_SEPARATOR)
                .append(sumBuy).append(System.lineSeparator());
        builder.append(RESULT_WORD).append(COMMA_SEPARATOR).append(total);
        result = builder.toString();

        System.out.println(result);
        writeFile(result, toFileName);
    }

    private void writeFile(String result, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}
