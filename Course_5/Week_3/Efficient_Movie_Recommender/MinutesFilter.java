public class MinutesFilter  implements Filter{
    int min_mins;
    int max_mins;

    public MinutesFilter(int min_mins, int max_mins) {
        this.min_mins = min_mins;
        this.max_mins = max_mins;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min_mins && MovieDatabase.getMinutes(id) <= max_mins;
    }
}