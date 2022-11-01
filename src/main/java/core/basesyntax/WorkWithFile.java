package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder reportBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splited = value.split(",");
                if (splited[OPERATION_TYPE_INDEX].equals("supply")) {
                    supplySum = supplySum + Integer.parseInt(splited[AMMOUNT_INDEX]);
                } else if (splited[OPERATION_TYPE_INDEX].equals("buy")) {
                    buySum = buySum + Integer.parseInt(splited[AMMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
            String result = String.valueOf(reportBuilder.append("supply,").append(supplySum)
                    .append(System.lineSeparator())
                    .append("buy,").append(buySum).append(System.lineSeparator())
                    .append("result,").append(supplySum - buySum));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,true));
            bufferedWriter.write(result);
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
