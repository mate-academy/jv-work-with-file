package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        File fileReade = new File(fromFileName);
        StringBuilder stringBuilder;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReade));
            stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String[] allOperation = stringBuilder.toString().split(System.lineSeparator());
            for (String operation : allOperation) {
                String[] oneOperation = operation.split(",");
                if (oneOperation[OPERATION_TYPE].equals(OPERATION_TYPE_BUY)) {
                    buy += Integer.parseInt(oneOperation[AMOUNT]);
                } else if (oneOperation[OPERATION_TYPE].equals(OPERATION_TYPE_SUPPLY)) {
                    supply += Integer.parseInt(oneOperation[AMOUNT]);
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] resultData = new String[]{"supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result};
        File fileWrite = new File(toFileName);
        for (String data : resultData) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(fileWrite, true))) {
                bufferedWriter.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write file", e);
            }
        }
    }
}
