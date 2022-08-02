package core.basesyntax;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Writer writer = new Writer();
        Reader reader = new Reader();
        writer.writeToFile(reader.readFromFile(fromFileName), toFileName);
    }
}
