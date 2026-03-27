package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyInt = 0;
        int buyInt = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = "";

            while ((value = reader.readLine()) != null) {
                String firstString = value.split(",")[0];
                int secondInt = Integer.parseInt(value.split(",")[1]);
                if (firstString.equals("supply")) {
                    supplyInt += secondInt;
                } else {
                    buyInt += secondInt;
                }
            }
            int resultInt = supplyInt - buyInt;
            String supply = "supply," + supplyInt;
            String buy = "buy," + buyInt;
            String result = "result," + resultInt;
            String[] list = { supply, buy, result };
            for (String sentence : list) {
                Files.write(Paths.get(toFileName),
                        (sentence + "\n").getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
