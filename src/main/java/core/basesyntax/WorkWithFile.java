package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    private static final String ENTER = System.lineSeparator();
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_TYPE_OF_OPERATION = 0;
    private static final int INDEX_AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String[]> listOfData = getDataFromFile(fromFileName);
        writeDataToFile(toFileName, listOfData);
    }

    public static List<String[]> getDataFromFile(String fromFile) {
        List<String[]> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            while (bufferedReader.ready()) {
                String[] rows = bufferedReader.readLine().split(COMMA);
                list.add(rows);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int countTheResult(List<String[]> allData, String position) {
        int result = 0;
        for (String[] str : allData) {
            if (str[INDEX_TYPE_OF_OPERATION].equals(position)) {
                result += Integer.parseInt(str[INDEX_AMMOUNT]);
            }
        }
        return result;
    }

    public static void writeDataToFile(String toFileName, List<String[]> listOfData) {
        List<String> listOfPosition = new ArrayList<>();
        int supplyResult = 0;

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            listOfPosition.add(SUPPLY);
            listOfPosition.add(BUY);
            for (String lst : listOfPosition) {
                int result = countTheResult(listOfData, lst);
                bufferedWriter.write(lst + COMMA + result + ENTER);

                if (supplyResult == 0) {
                    supplyResult = result;
                } else {
                    int difference = supplyResult - result;
                    bufferedWriter.write("result," + difference);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
