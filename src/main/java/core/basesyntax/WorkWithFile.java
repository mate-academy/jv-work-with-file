package core.basesyntax;

public class WorkWithFile {
    private FileReader fileReader = new FileReader();
    private FileWriter fileWriter = new FileWriter();
    private ReportCreator reportCreator = new ReportCreator();

    public void getStatistic(String fromFileName, String toFileName) {
        fileWriter.writeToFile(toFileName, reportCreator
                .createReport(fileReader.readFile(fromFileName)));
    }
}
