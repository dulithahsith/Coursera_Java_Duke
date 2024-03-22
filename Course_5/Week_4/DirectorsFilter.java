public class DirectorsFilter implements Filter {
    private String directorList;

    public DirectorsFilter(String directors) {
        this.directorList= directors;
    }

    @Override
    public boolean satisfies(String id){
        String[] directors=directorList.split(",");
        for(String director:directors){
            if(MovieDatabase.getDirector(id).contains(director)){
                return true;
            }
        }
        return false;
    }
}