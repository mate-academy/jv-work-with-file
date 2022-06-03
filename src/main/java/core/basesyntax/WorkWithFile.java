package core.basesyntax;

import java.io.*;
import java.util.Arrays;

public class WorkWithFile {
    private final int OPERATION_TYPE = 0;
    private final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0, supply = 0, result = 0;
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
            String[] allOperation = stringBuilder.toString().split("\r\n");
            for (String operation : allOperation) {
                String[] oneOperation = operation.split(",");
                if (oneOperation[OPERATION_TYPE].equals("buy")) {
                    buy += Integer.parseInt(oneOperation[AMOUNT]);
                } else if (oneOperation[OPERATION_TYPE].equals("supply")) {
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
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite, true))) {
                bufferedWriter.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write file", e);
            }
        }
    }
}
