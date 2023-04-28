package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_KEYWORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(calculateReadDataFromFile(fromFileName), toFileName);
    }

    private String calculateReadDataFromFile(String fromFileName) {
        int supplyAllSum = 0;
        int buyAllSum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                if (str.contains(BUY_KEYWORD)) {
                    buyAllSum += Integer.parseInt(str.replaceAll("[a-zA-Z[.][,][_][-]]", ""));
                } else {
                    supplyAllSum += Integer.parseInt(str.replaceAll("[a-zA-Z[.][,][_][-]]", ""));
                }
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: ", e);
        }
        return "supply," + supplyAllSum
                + "\nbuy," + buyAllSum + "\nresult," + (supplyAllSum - buyAllSum);
    }

    private void writeDataToFile(String calculatedData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculatedData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file: ", e);
        }
    }
}
