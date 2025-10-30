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
    private static final String RESULT = "result";
    private final List<String> stringList = new ArrayList<>();
    private final ProductsMap productsMap = new ProductsMap();

    public void getStatistic(String fromFileName, String toFileName) {
        parseLines(readFile(fromFileName));
        writeFile(toFileName);
    }

    private List<String> readFile(String fromFileName) {
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

    private void parseLines(List<String> stringList) {
        String[][] stringArray = new String[stringList.size()][TWO];

        for (int i = 0; i < stringList.size(); i++) {
            stringArray[i] = stringList.get(i).split(String.valueOf(COMMA));
        }

        stringList.clear();

        for (String[] strings : stringArray) {
            productsMap.addProduct(strings[ZERO], Integer.parseInt(strings[ONE]));
        }
    }

    private String writeString() {
        int result = productsMap.isName(SUPPLY) ? productsMap.getQuantity(SUPPLY) : ZERO;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(COMMA)
                .append(productsMap.getQuantity(SUPPLY))
                .append(System.lineSeparator());

        for (String product : productsMap.getProducts()) {
            if (!product.equals(SUPPLY)) {
                result -= productsMap.getQuantity(product);
                stringBuilder.append(product)
                        .append(COMMA)
                        .append(productsMap.getQuantity(product))
                        .append(System.lineSeparator());
            }
        }

        stringBuilder.append(RESULT).append(COMMA).append(result);

        return stringBuilder.toString();
    }

    private void writeFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(writeString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        } finally {
            productsMap.clearProducts();
        }
    }
}
