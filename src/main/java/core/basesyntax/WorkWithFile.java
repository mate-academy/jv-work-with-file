package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private final int[] purchaseSale = new int[2];

    private void getPurchaseSale(String fromFileName) {
        StringBuilder tableInFile = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] newValue = value.split(",");
                if (value.startsWith("supply")) {
                    supply += Integer.parseInt(newValue[1]);
                } else if (value.startsWith("buy")) {
                    buy += Integer.parseInt(newValue[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find file", e);
        }

        purchaseSale[0] = supply;
        purchaseSale[1] = buy;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        getPurchaseSale(fromFileName);
        String report = "supply," + purchaseSale[0] + System.lineSeparator()
                + "buy," + purchaseSale[1] + System.lineSeparator()
                + "result," + (purchaseSale[0] - purchaseSale[1]);

        File writeResult = new File(toFileName);
        try {
            writeResult.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't can't create a new file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeResult))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

}
