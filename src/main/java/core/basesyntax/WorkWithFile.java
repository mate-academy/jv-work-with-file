package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_BY = ",";
    private static final String SUPPLY_INDEX = "supply";
    private static final String BUY_INDEX = "buy";
    private static final String RESULT_INDEX = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        String valueF = "";
        int supplyInt = 0;
        int buyInt = 0;
        int sum = 0;

        File files = new File(toFileName);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));

            while ((valueF = reader.readLine()) != null) {
                if ((valueF.split(SPLIT_BY)[0]).equals(SUPPLY_INDEX)) {
                    supplyInt += Integer.parseInt(valueF.split(SPLIT_BY)[1]);
                } else {
                    buyInt += Integer.parseInt(valueF.split(SPLIT_BY)[1]);
                }
            }
            sum = supplyInt - buyInt;
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String[] report = {SUPPLY_INDEX + SPLIT_BY + supplyInt,
                BUY_INDEX + SPLIT_BY + buyInt, RESULT_INDEX + SPLIT_BY + sum};
        for (String user : report) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(files, true))) {
                bufferedWriter.write(user + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write to the file",e);
            }
        }
    }
}
