package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splited = value.split(",");
                int parsed = Integer.parseInt(splited[1]);
                if (splited[0].equals(SUPPLY)) {
                    supplyCounter += parsed;
                } else if (splited[0].equals(BUY)) {
                    buyCounter += parsed;
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Cann't read file...", e);
        }
        int result = supplyCounter - buyCounter;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(SUPPLY + "," + supplyCounter);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + "," + buyCounter);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file...", e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("test.csv", "AppleStat.csv");
    }
}


