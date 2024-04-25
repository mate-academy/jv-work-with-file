package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String row = bufferedReader.readLine();
            while (row != null) {
                if (row.startsWith(SUPPLY)) {
                    String amountColumn = row.substring(SUPPLY.length());
                    supply += Integer.parseInt(amountColumn);
                } else if (row.startsWith(BUY)) {
                    String amountColumn = row.substring(BUY.length());
                    buy += Integer.parseInt(amountColumn);
                }
                row = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Can't read the file!" + e);
        }

        int result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + supply);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + buy);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + result);
        } catch (IOException e) {
            System.out.println("Can't write to file!" + e);
        }
    }
}
