package core.basesyntax;

import java.io.File;
import java.util.List;

public class WorkWithFile {
    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private final FileReader fileReader = new core.basesyntax.impl.FileReaderImpl();
    private final FileWriter fileWriter = new core.basesyntax.impl.FileWriterImpl();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = fileReader.readFromFile(String.valueOf(new File(fromFileName)));
        String report = buildReport(lines);
        fileWriter.writeToFile(String.valueOf(new File(toFileName)), report);
    }

    private String buildReport(List<String> lines) {
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] splitterLine = line.split(",");
            if (splitterLine[TYPE_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(splitterLine[AMOUNT_INDEX]);
            } else if (splitterLine[TYPE_INDEX].equals(BUY)) {
                buy += Integer.parseInt(splitterLine[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(result);
        return builder.toString();
    }
}
