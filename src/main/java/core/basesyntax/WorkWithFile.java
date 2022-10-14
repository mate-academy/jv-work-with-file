package core.basesyntax;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        MethodsForFile methodsForFile = new MethodsForFile();
        String completedText = methodsForFile.getReadFile(fromFileName);
        methodsForFile.getWriteFile(toFileName, completedText);
    }
}
