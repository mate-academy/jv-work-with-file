package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX = 0;
    private static final int VALUE = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] list = getFile(fromFileName);
        int[] totals = calculate(list);
        String values = getStringResult(totals);
        writeResult(values, toFileName);
    }

    private String[] getFile(String fromFileName) {
        int count = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String csv = bufferedReader.readLine();
            while (csv != null) {
                count++;
                csv = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't process file", e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String[] products = new String[count];
            int i = 0;
            String csv1 = reader.readLine();
            while (csv1 != null) {
                products[i] = csv1;
                i++;
                csv1 = reader.readLine();
            }
            return products;

        } catch (IOException e) {
            throw new RuntimeException("Can't process file", e);
        }
    }

    private int[] calculate(String[] products) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String product : products) {
            String[] sep = product.split(",");
            if (sep[INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(sep[VALUE]);
            } else {
                buy += Integer.parseInt(sep[VALUE]);
            }
        }
        result = supply - buy;
        int[] totals = {supply, buy, result};
        return totals;
    }

    private String getStringResult(int[] totals) {
        String calculated = SUPPLY + "," + totals[0] + System.lineSeparator()
                + BUY + "," + totals[1] + System.lineSeparator()
                + RESULT + "," + totals[2] + System.lineSeparator();
        return calculated;
    }

    private void writeResult(String calculated, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(calculated);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
