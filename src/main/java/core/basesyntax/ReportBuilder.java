package core.basesyntax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ReportBuilder {

    public static final String SEPARATOR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public Collection<String> buildReport(Collection<String> inputData) {
        int supply = 0;
        int buy = 0;
        ArrayList<String> result = new ArrayList<>();
        for (String row : inputData) {
            String[] columns = (row.split(SEPARATOR));
            if (Objects.equals(columns[0], SUPPLY)) {
                supply += Integer.parseInt(columns[1]);
            }
            if (Objects.equals(columns[0], BUY)) {
                buy += Integer.parseInt(columns[1]);
            }
        }
        result.add(SUPPLY + SEPARATOR + supply);
        result.add(BUY + SEPARATOR + buy);
        result.add(RESULT + SEPARATOR + (supply - buy));
        return result;
    }
}
