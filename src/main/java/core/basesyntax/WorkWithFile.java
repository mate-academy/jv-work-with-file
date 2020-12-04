package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String[] setOfNames = new String[]{"supply", "buy"};
    private StringBuilder dataForStatistics = new StringBuilder();
    private List<String> fullStrings = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        calculateResult();
        writeToFile(toFileName);
    }

    public void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String nameProduct = bufferedReader.readLine();
            while (nameProduct != null) {
                fullStrings.add(nameProduct);
                nameProduct = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }

        for (String str : setOfNames) {
            dataForStatistics.append(str).append(",");
            int count = 0;
            for (int i = 0; i < fullStrings.size(); i++) {
                if (str.equals(fullStrings.get(i).substring(0, fullStrings.get(i).indexOf(",")))) {
                    count += Integer.parseInt(fullStrings.get(i)
                            .substring(fullStrings.get(i).indexOf(",") + 1));
                }
            }
            dataForStatistics.append(count).append(System.lineSeparator());
        }
    }

    public void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(dataForStatistics.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + toFileName, e);
        }
    }

    public void calculateResult() {
        String[] statistics = dataForStatistics.toString().split(System.lineSeparator());
        int buy = Integer.parseInt(statistics[0].substring(statistics[0].indexOf(",") + 1));
        int supply = Integer.parseInt(statistics[1].substring(statistics[1].indexOf(",") + 1));
        int result = buy >= supply ? buy - supply : supply - buy;

        dataForStatistics.append("result").append(",").append(result);
    }
}
