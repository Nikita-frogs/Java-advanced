public class DailyReport extends ReportGenerator{
    @Override
    protected void collectData() {
        System.out.println("Collecting daily data...");
    }

    @Override
    protected void analyzeData() {
        System.out.println("Analyzing daily data...");
    }

    @Override
    protected void formatReport() {
        System.out.println("Formatting daily report...");
    }
}
