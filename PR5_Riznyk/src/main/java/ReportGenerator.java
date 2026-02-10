abstract class ReportGenerator {
    public final void generate() {
        collectData();
        analyzeData();
        formatReport();
    }

    protected abstract void collectData();

    protected abstract void analyzeData();

    protected abstract void formatReport();
}
