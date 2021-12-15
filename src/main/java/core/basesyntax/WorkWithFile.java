package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String[] receivedData = stringBuilder.toString().split(System.lineSeparator());
            ArrayList<String> result = new ArrayList<>();
            int supplySum = 0;
            int buySum = 0;
            for (int i = 0; i < receivedData.length; i++) {
                String operation = receivedData[i].substring(0, receivedData[i].indexOf(","));
                int sumByOperration = 0;
                for (int j = 0; j < receivedData.length; j++) {
                    if (operation.equals(receivedData[j]
                            .substring(0, receivedData[j].indexOf(",")))) {
                        sumByOperration += Integer.parseInt(receivedData[j]
                                .substring(receivedData[j].indexOf(",") + 1));
                    }
                }
                if (operation.equals("supply")) {
                    supplySum = sumByOperration;
                } else if (operation.equals("buy")) {
                    buySum = sumByOperration;
                }
            }
            result.add("supply," + supplySum);
            result.add("buy," + buySum);
            result.add("result," + (supplySum - buySum));
            File file = new File(toFileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("cant create new file", e);
            }
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter((new FileWriter(toFileName, true)));
                for (String record : result) {
                    bufferedWriter.write(record + System.lineSeparator());
                }
            } catch (IOException e) {
                throw new RuntimeException("cant write to file", e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException("cant close bufferedWriter");
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("cant read file");
        }
    }
}

