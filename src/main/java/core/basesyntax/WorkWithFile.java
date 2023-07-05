package core.basesyntax;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply,";
    private static final String BUY_WORD = "buy,";
    private static final String RESULT_WORD = "result,";
    private String result = "";

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> list = new ArrayList<>();
        File myFile = new File(fromFileName);
        Scanner scanner;
        try (FileReader filereader = new FileReader(myFile)) {
            scanner = new Scanner(filereader);
            while (scanner.hasNextLine()) {
                list.add(Arrays.toString(scanner.nextLine().split(System.lineSeparator())));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }

        String[] words = new String[list.size()];
        words = list.toArray(words);

        getResult(words);
        writeFile(result, toFileName);
        result = "";
    }

    private String getResult(String[] words) {
        int sumSupply = 0;
        int sumBuy = 0;
        int total;

        StringBuilder builder = new StringBuilder(result);
        for (String word : words) {
            if (word.contains(SUPPLY_WORD)) {
                sumSupply += Integer.parseInt(word.replaceAll("[^\\d+$]", ""));
            }
            if (word.contains(BUY_WORD)) {
                sumBuy += Integer.parseInt(word.replaceAll("[^\\d+$]", ""));
            }
        }
        total = sumSupply - sumBuy;
        builder.append(SUPPLY_WORD).append(sumSupply).append(System.lineSeparator());
        builder.append(BUY_WORD).append(sumBuy).append(System.lineSeparator());
        builder.append(RESULT_WORD).append(total);
        result = builder.toString();
        System.out.println(result);
        return result;
    }

    private void writeFile(String result, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}
