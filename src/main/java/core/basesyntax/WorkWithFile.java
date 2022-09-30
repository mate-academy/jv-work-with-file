package core.basesyntax;

public class WorkWithFile {

    public static final BuffWriter buffWriter = new BuffWriter();
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String REPORT = "result";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final int DEFAULT_NUMBER = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        buffWriter.setFileToRead(fromFileName);
        buffWriter.setFileToWrite(toFileName);
        buffWriter.writeInFile();
    }
}
