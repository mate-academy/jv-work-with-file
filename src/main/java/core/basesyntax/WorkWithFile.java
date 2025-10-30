package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final char COMMA = ',';
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        final List<String> stringList = new ArrayList<>();
        final ProductsMap productsMap = new ProductsMap();
        parseLines(readFile(fromFileName, stringList), productsMap);
        writeFile(toFileName, productsMap);
    }

    private List<String> readFile(String fromFileName, List<String> stringList) {
        File file = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();

            while (value != null && !value.isEmpty()) {
                stringList.add(value);
                value = bufferedReader.readLine();
            }

            return stringList;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file.", e);
        }
    }

    private void parseLines(List<String> stringList, ProductsMap productsMap) {
        String[][] stringArray = new String[stringList.size()][TWO];

        for (int i = 0; i < stringList.size(); i++) {
            stringArray[i] = stringList.get(i).split(String.valueOf(COMMA));
        }

        stringList.clear();

        for (String[] strings : stringArray) {
            productsMap.addProduct(strings[ZERO], Integer.parseInt(strings[ONE]));
        }
    }

    private String writeString(ProductsMap productsMap) {
        int supplyQuantity = productsMap.getQuantity(SUPPLY);
        int buyQuantity = productsMap.getQuantity(BUY);
        int result = supplyQuantity - buyQuantity;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(COMMA)
                .append(supplyQuantity)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(buyQuantity)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(result);
        return stringBuilder.toString();
    }

    private void writeFile(String toFileName, ProductsMap productsMap) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(writeString(productsMap));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        }
    }
}
