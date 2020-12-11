import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UrlAddressState {

    private Date modificationTime;
    private URL url;
    private String URL;
    private URLConnection connection;

    public UrlAddressState(URL url,String URL,URLConnection connection){
        this.url = url;
        this.URL = URL;
        this.connection = connection;
    }

    public void setTime(long time){
        modificationTime = new Date(time);
    }

    public Date getTime(){
        return modificationTime;
    }

    public URL getUrl(){
        return url;
    }
    public URLConnection getConnection(){
        return connection;
    }

    public String getUrlAsString(){
        return URL;
    }

    public static boolean containsUrl(UrlAddressState[] urlAddressStates, String url) {
        for (UrlAddressState u: urlAddressStates) {
            if(u.getUrlAsString().equals(url)) return true;
        }
        return false;
    }


    public String toString()
    {
        return "Url: " + URL + " last modified: " + modificationTime + "\n";
    }

}
