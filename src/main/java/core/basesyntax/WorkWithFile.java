package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringFromFile = new ArrayList<>();
        StringBuilder report = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                stringFromFile.add(read);
                read = bufferedReader.readLine();
            }
            int buy = 0;
            int supply = 0;

            for (String s : stringFromFile) {
                String[] split = s.split(",");
                int digit = Integer.decode(split[1]);
                if (split[0].equals(OPERATION_TYPE_BUY)) {
                    buy += digit;
                } else if (split[0].equals(OPERATION_TYPE_SUPPLY)) {
                    supply += digit;
                }
            }
            int result = supply - buy;
            report.append("supply,")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (int i = 0; i < report.length(); i++) {
                bufferedWriter.write(report.charAt(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
