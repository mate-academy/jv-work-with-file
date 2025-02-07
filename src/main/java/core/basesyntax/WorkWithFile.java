package core.basesyntax;

public class WorkWithFile {
    private WriteFile writeFile = new WriteFile();

    public void getStatistic(String fromFileName, String toFileName) {

        writeFile.writeFile(fromFileName, toFileName);

    }
}
