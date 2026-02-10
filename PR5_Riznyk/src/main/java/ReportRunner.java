public class ReportRunner {
    public static void main(String[] args) {
        ReportGenerator dailyReport = new DailyReport();
        dailyReport.generate();
    }
}
