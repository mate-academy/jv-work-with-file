package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splited = value.split(",");
                int parsed = Integer.parseInt(splited[1]);
                if (splited[0].equals(SUPPLY)){
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
        System.out.println(result + " " + supplyCounter + " " + buyCounter );
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "AppleStat.csv");
    }
}


