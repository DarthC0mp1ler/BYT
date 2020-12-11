public class Observer {

    private static int ID = 0;

    private int id;


    private String url;

    public Observer(String url){
        this.url = url;
        id = ID++;
    }

    public void update() {
        System.out.println("Observer " + id + ": URL " + url + " was modified");
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return "Observer " + id + ", observes " + url + "\n";
    }
}
