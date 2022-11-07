package core.basesyntax;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        Reader reader = new Reader();
        Writer writer = new Writer();
        writer.writeData(reader.readFromFile(fromFileName),toFileName);
        writer.createReport(reader.readFromFile(fromFileName));
    }
}
