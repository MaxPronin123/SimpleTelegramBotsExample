import java.net.*;
import java.io.*;

public class getRate {
    public String load() {
        URL cbr = null;
        InputStream dis = null;
        BufferedReader is = null;
        String fullString = "";
        try {
           // cbr = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                fullString += line;
            }
            reader.close();
        } catch(Exception e) {
            System.out.println("Url error: " + e);
        }
        return fullString;
    }
}
