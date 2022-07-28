package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    private static final String SUPPLY_CONDITION = "supply";
    private static final String BUY_CONDITION = "buy";
    private static final String REGEX_CONDITION = "\\D";
    private StringBuilder stringBuilder = new StringBuilder();
    private int supplySum = 0;
    private int buySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {

        calculateResultForDay(fromFileName);

    }
        //отдал стринг билдер
    private String readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = null;
            try {
                value = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read file " + e);
            }
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                try {
                    value = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write file in StringBuilder " + e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + e);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + e);
        }
        return stringBuilder.toString();
    }
        //отдал массив с двумя индексами
    public int[] calculateResultForDay(String fromFileNam) {
        String[] sortedArray = readDataFromFile(fromFileNam).split(System.lineSeparator());
        for (String line : sortedArray) {
            //получил массив который нужно просчитать
            sortedArray = line.split(" ");
            //получил число из линии
            int numberFromLine = Integer.parseInt(line.replaceAll(REGEX_CONDITION, ""));
            //посчитали числа
            if (sortedArray[0].contains(SUPPLY_CONDITION)) {
                supplySum += numberFromLine;
            }
            if (sortedArray[0].contains(BUY_CONDITION)){
                buySum += numberFromLine;
            }
        }
        return new int[] {buySum, supplySum};
    }

    public static void main(String[] args) {
        System.out.println(new WorkWithFile().calculateResultForDay("apple.csv"));

    }
}
