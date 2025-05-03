package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = getReport(fromFileName);
        writeReportToFile(report, toFileName);
    }

    private String getReport(String fromFileName) {
        int buy = 0;
        int supply = 0;
        final String strBuy = "buy";
        final String strSupply = "supply";

        File fileFrom = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] arrValue = value.split(","); //\W+
                switch (arrValue[0]) {
                    case strBuy:
                        buy += Integer.parseInt(arrValue[1]);
                        break;
                    case strSupply:
                        supply += Integer.parseInt(arrValue[1]);
                        break;
                    default:
                        continue;
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strSupply).append(",").append(supply).append(System.lineSeparator());
        stringBuilder.append(strBuy).append(",").append(buy).append(System.lineSeparator());
        stringBuilder.append("result,").append(supply - buy);

        return stringBuilder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try {
            Files.write(Paths.get(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file");
        }

    }
}
