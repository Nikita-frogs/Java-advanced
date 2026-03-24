package Static_nested_vs_inner;

public class Library {
    private final String name = "Static_nested_vs_inner.Library";

    public class Book{
        private String title = "Man and the sea";
        private String author;
        public String bookLabel(){
            title+=" " + name;
            return title;
        }
    }
    public Book book(){
        return new Book();
    }
}
