package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY_DEFINITION = "supply";
    private static final String BUY_DEFINITION = "buy";
    private static final String RESULT_DEFINITION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        writeReportIntoFile(mkReport(data),toFileName);
    }

    private String readFile(String readFileName) {
        StringBuilder build = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFileName))) {
            String value = reader.readLine();
            while (value != null) {
                build.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Doesn't find file", ex);
        }
        return build.toString();
    }

    private String mkReport(String data) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] arrayData = data.split(" ");
        for (String stat : arrayData) {
            if (stat.contains(SUPPLY_DEFINITION)) {
                supply += Integer.parseInt(stat.substring(SUPPLY_DEFINITION.length() + 1));
            } else {
                buy += Integer.parseInt(stat.substring(BUY_DEFINITION.length() + 1));
            }
        }
        result = supply - buy;
        StringBuilder build = new StringBuilder();
        build.append(SUPPLY_DEFINITION).append(",").append(supply).append(System.lineSeparator());
        build.append(BUY_DEFINITION).append(",").append(buy).append(System.lineSeparator());
        build.append(RESULT_DEFINITION).append(",").append(result).append(System.lineSeparator());
        return build.toString();
    }

    private void writeReportIntoFile(String report, String endfile) {
        File file = new File(endfile);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException("File didn't create", ex);
        }
        try {
            Files.write(file.toPath(),report.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new RuntimeException("File didn't create", ex);
        }
    }
}
