package core.basesyntax;

import java.io.File;
import java.util.List;

public class WorkWithFile {

    private static final String SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private final FileRead fileRead = new core.basesyntax.impl.FileRead();
    private final FileWrite fileWrite = new core.basesyntax.impl.FileWrite();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> read = fileRead.readFile(new File(fromFileName));
        String report = buildReport(read);
        fileWrite.writeFile(new File(toFileName), report);
    }

    private String buildReport(List<String> read) {
        int supply = 0;
        int buy = 0;
        for (String readInfo : read) {
            String[] info = readInfo.split(SEPARATOR);
            if (info[0].equals(SUPPLY)) {
                supply += Integer.parseInt(info[1]);
            } else if (info[0].equals(BUY)) {
                buy += Integer.parseInt(info[1]);
            }
        }
        int result = supply - buy;
        return SUPPLY + SEPARATOR + supply + LINE_SEPARATOR
                + BUY + SEPARATOR + buy + LINE_SEPARATOR
                + RESULT + SEPARATOR + result;
    }
}
