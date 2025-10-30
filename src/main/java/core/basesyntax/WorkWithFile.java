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
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        ProductsMap productsMap = parseLines(lines);
        writeFile(toFileName, productsMap);
    }

    private List<String> readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> stringList = new ArrayList<>();

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

    private ProductsMap parseLines(List<String> stringList) {
        ProductsMap productsMap = new ProductsMap();
        for (int i = 0; i < stringList.size(); i++) {
            String name = stringList.get(i).split(Constants.DELIMITER)[0];
            int quantity = Integer.parseInt(stringList.get(i).split(Constants.DELIMITER)[1]);
            productsMap.addProduct(name, quantity);
        }
        return productsMap;
    }

    private String writeString(ProductsMap productsMap) {
        int supplyQuantity = productsMap.getQuantity(Constants.SUPPLY);
        int buyQuantity = productsMap.getQuantity(Constants.BUY);
        int result = supplyQuantity - buyQuantity;

        return Constants.SUPPLY
                + Constants.DELIMITER
                + supplyQuantity
                + System.lineSeparator()
                + Constants.BUY
                + Constants.DELIMITER
                + buyQuantity
                + System.lineSeparator()
                + Constants.RESULT
                + Constants.DELIMITER
                + result;
    }

    private void writeFile(String toFileName, ProductsMap productsMap) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(writeString(productsMap));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        }
    }
}
