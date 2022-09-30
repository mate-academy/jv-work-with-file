package core.basesyntax;

public class SplitAllSpaces {

    public String[] splitSpaces() {
        String a = WorkWithFile.buffWriter.readFromFile();
        return a.split(WorkWithFile.SPACE);
    }
}
