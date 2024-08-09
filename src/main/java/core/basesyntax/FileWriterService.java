package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterService {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void writeToFile(Report report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(COMMA).append(report.getSuppliesAmount())
                    .append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(report.getBuysAmount())
                    .append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(report.getResult())
                    .append(System.lineSeparator());
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
