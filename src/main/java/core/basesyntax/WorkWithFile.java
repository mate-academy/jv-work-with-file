package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int INDEX = 0;
    private static final int VALUE = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            String csv = bufferedReader.readLine();
            while (csv != null) {
                String[] split = csv.split(",");
                if (split[INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(split[VALUE]);
                } else {
                    buy += Integer.parseInt(split[VALUE]);
                }
                result = supply - buy;
                csv = bufferedReader.readLine();
            }
            bufferedWriter.write(SUPPLY + "," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + "," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + "," + result);


        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
