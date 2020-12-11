import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ModificationCheckerTest {

    private Observer observer0;
    private Observer observer1;
    private Observer observer2;

    private String url1;
    private String url2;

    private URL url_1;
    private URL url_2;

    @Before
    public void setUP(){
        url1 = "http://www.pja.edu.pl/";
        url2 = "http://www.youtube.com";

        observer0 = new Observer(url1);
        observer1 = new Observer(url2);
        observer2 = new Observer(url2);

        try {
            url_1 = new URL(url1);
            url_2 = new URL(url2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void startTest(){

        URLConnection connection1 = mock(URLConnection.class);
        URLConnection connection2 = mock(URLConnection.class);

//        try {
//            when(url_1.openConnection()).thenReturn(connection1);
//            when(url_2.openConnection()).thenReturn(connection2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        UrlAddressState state1 = new UrlAddressState(url_1, url1, connection1);
        state1.setTime(111111L);
        UrlAddressState state2 = new UrlAddressState(url_2, url2, connection2);
        state2.setTime(System.currentTimeMillis());

        when(connection1.getLastModified()).thenReturn(1L);
        when(connection2.getLastModified()).thenReturn(System.currentTimeMillis());

        ModificationChecker modificationChecker = new ModificationChecker(
                new UrlAddressState[]{state1,state2}
                ,observer0,observer1,observer2);
        modificationChecker.startCheck();
    }
}
