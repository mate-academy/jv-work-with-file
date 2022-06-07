package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SIGN = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        File fileReader = new File(fromFileName);
        int supplyCount = 0;
        int buyCount = 0;
        int result;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReader));
            String value;
            StringBuilder builder = new StringBuilder();
            while ((value = bufferedReader.readLine()) != null) {
                builder.append(value).append(SEPARATOR);
            }
            String[] file = builder.toString().split(SEPARATOR);
            for (String files : file) {
                String[] reportFile = files.split(SIGN);
                if (reportFile[INDEX_ZERO].equals(BUY)) {
                    buyCount += Integer.parseInt(reportFile[INDEX_ONE]);
                } else {
                    supplyCount += Integer.parseInt(reportFile[INDEX_ONE]);
                }
            }
            result = supplyCount - buyCount;
        } catch (IOException e) {
            throw new RuntimeException("Read to file " + fromFileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(SIGN).append(supplyCount)
                .append(SEPARATOR).append(BUY).append(SIGN)
                .append(buyCount).append(SEPARATOR).append(RESULT)
                .append(SIGN).append(result);

        File resultFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile, true))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("write to file " + toFileName, e);
        }
    }
}
