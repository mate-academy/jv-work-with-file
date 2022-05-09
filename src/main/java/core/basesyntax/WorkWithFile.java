package core.basesyntax;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();
        ReportCreator reportCreator = new ReportCreator();
        fileWriter.writeToFile(toFileName, reportCreator
                .createReport(fileReader.readFile(fromFileName)));
    }
}
