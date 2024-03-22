import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        this.filters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
        boolean answer = true;
        for (Filter f : filters) {
            answer = answer && f.satisfies(qe);
        }
        return answer;
    }

    public String getName() {
        String names = "";
        for (Filter f : filters) {
            names += f.getName();
            names += " ";
        }
        return names;
    }
}