package core.basesyntax;

public class WorkWithFile {
    private static final FileReader fileReader = new FileReader();
    private static final StatisticCreator statisticCreator = new StatisticCreator();
    private static final WriteToFile writeToFile = new WriteToFile();

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = fileReader.fileReader(fromFileName);
        String report = statisticCreator.statisticCreator(infoFromFile);
        writeToFile.writeToFile(report, toFileName);
    }
}
