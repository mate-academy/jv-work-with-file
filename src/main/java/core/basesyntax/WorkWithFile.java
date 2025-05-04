package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String FIRST_PARAMETER = "supply";
    private static final String SECOND_PARAMETER = "buy";
    private static final String THIRD_PARAMETER = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineOfFile = bufferedReader.readLine();
            while (lineOfFile != null) {
                String[] arr = lineOfFile.split(",");
                if (arr[0].equals(FIRST_PARAMETER)) {
                    supply += Integer.parseInt(arr[1]);
                } else if (arr[0].equals(SECOND_PARAMETER)) {
                    buy += Integer.parseInt(arr[1]);
                }
                lineOfFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem with input file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(FIRST_PARAMETER).append(",")
                    .append(supply).append(System.lineSeparator());
            stringBuilder.append(SECOND_PARAMETER).append(",")
                    .append(buy).append(System.lineSeparator());
            stringBuilder.append(THIRD_PARAMETER).append(",").append(supply - buy);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Problem with output file", e);
        }

    }
}
