package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int WORD_INDEX = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_VALUE = "result";
    private int totalSupply;
    private int totalBuy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = getResultText(readText(fromFileName));
        writeText(toFileName, statistic);
    }

    private void writeText(String toFileName, String statistic) {
        try {
            Files.writeString(new File(toFileName).toPath(), statistic);
        } catch (IOException e) {
            throw new RuntimeException("can't write to file: " + toFileName, e);
        }
    }

    private List<String> readText(String fromFileName) {
        List<String> list;
        try {
            list = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("wrong file path: " + fromFileName, e);
        }
        return list;
    }

    private String getResultText(List<String> list) {
        totalSupply = 0;
        totalBuy = 0;
        for (String s : list) {
            String[] splitLine = s.split(",");
            setInformation(splitLine[WORD_INDEX], splitLine[VALUE_INDEX]);
        }
        result = totalSupply - totalBuy;
        return SUPPLY + "," + totalSupply
                + System.lineSeparator() + BUY + ","
                + totalBuy + System.lineSeparator()
                + RESULT_VALUE + "," + result;
    }

    private void setInformation(String name, String value) {
        switch (name) {
            case SUPPLY:
                totalSupply += Integer.parseInt(value);
                break;
            case BUY:
                totalBuy += Integer.parseInt(value);
                break;
            default:
        }
    }
}
