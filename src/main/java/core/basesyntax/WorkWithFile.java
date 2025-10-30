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
    private final List<String> stringList = new ArrayList<>();
    private final ProductsMap productsMap = new ProductsMap();

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
    }

    public void readFile(String fromFileName) {
        File file = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();

            while (value != null && !value.isEmpty()) {
                stringList.add(value);
                value = bufferedReader.readLine();
            }
            String[][] stringArray = new String[stringList.size()][2];

            for (int i = 0; i < stringList.size(); i++) {
                stringArray[i] = stringList.get(i).split(",");
            }

            for (int i = 0; i < stringArray.length; i++) {
                productsMap.addProduct(stringArray[i][0], Integer.parseInt(stringArray[i][1]));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file.", e);
        } finally {
            stringList.clear();
        }
    }

    public void writeFile(String toFileName) {
        int result = productsMap.getQuantity("supply");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + productsMap.getQuantity("supply")
                    + System.lineSeparator());
            for (String product : productsMap.getProducts()) {
                if (!product.equals("supply")) {
                    result -= productsMap.getQuantity(product);
                    bufferedWriter.write(product + "," + productsMap.getQuantity(product)
                            + System.lineSeparator());
                }
            }
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        } finally {
            productsMap.clearProducts();
        }
    }
}
