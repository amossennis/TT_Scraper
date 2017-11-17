import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ShuttleScraper {
    public final String mainPage = "https://ucsdbus.com/simple/routes/";
    private final String gilmanMyers = "2092/stops/34893";
    public static void main(String[] args) throws IOException {
        String mainPage = "https://ucsdbus.com/simple/routes/";
        String gilmanMyers = "2092/stops/34893";
        Document doc = Jsoup.connect(mainPage + gilmanMyers).get();
        Elements listElements = doc.getElementsByTag("li");
        int size = listElements.size();
        ArrayList<String> times = new ArrayList<String>();
        for (int i=1; i < size; i++) {
            String[] parser = listElements.eq(i).toString().split(" ");
            int arrivalTimeMin = Integer.parseInt(parser[4]);

            //edge case: bus is currently arriving
            if(parser[2].equals("is")){
                System.out.println("Next bus is arriving soon.");
            }

            String[] timeParser = parser[7].split(":");
            int hour = Integer.parseInt(timeParser[0]);
            int min = Integer.parseInt(timeParser[1]);
            if(parser[8].equals("PM.")){
                hour += 12;
                hour = hour % 24;
            }
            //String newTime = "" + hour + ':' + min + ':' + "00";
            //DateFormat sdf = new SimpleDateFormat(newTime);
            //Date busDate = new Date();
            //busDate.
            //Date busDate = sdf.parse(newTime);
            //Date currDate = new Date();

            //times.add(parsed);
            System.out.println("Time until bus arrives: " + arrivalTimeMin + " Minutes.");

        }



    }
}
