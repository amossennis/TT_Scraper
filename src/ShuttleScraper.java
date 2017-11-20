import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShuttleScraper{
    public final String mainPage = "https://ucsdbus.com/simple/routes";
    public ArrayList<Bus> buses;
    public ArrayList<Stop> stops;

    public ShuttleScraper(){
        this.buses = new ArrayList<Bus>();
        this.stops = new ArrayList<Stop>();
    }

    public ArrayList<Bus> getBuses(){
        return this.buses;
    }
    public ArrayList<Stop> getStops(){
        return this.stops;
    }

    public void scrape() throws IOException {
        //Start by scraping main string to get all href links to the buses
        Document doc = Jsoup.connect(mainPage).get();
        Elements busLink = doc.select("a[href]");
        ArrayList<String> routeLinks = new ArrayList<String>();

        //Parses all individual route links from the UCSDBus main page
        for(int index = 0; index < busLink.size(); index++){
            String[] parser = busLink.eq(index).toString().split("\"");
            String[] doubleParse = parser[1].split("/");
            String correctExt = "/" + doubleParse[3] + "/stops";
            routeLinks.add(correctExt);
        }

        //Parses all stops from an individual route link
        //Creates a Bus object and Stop objects with which to store the information
        for(int index = 0; index < routeLinks.size(); index++){
            String fullLink = mainPage + routeLinks.get(index);
            doc = Jsoup.connect(fullLink).get();
            Elements stopLink = doc.select("a[href]");
            ArrayList<String> stopList = new ArrayList<String>();

            //Parses individual stop links from HTML
            for(int i = 0; i < stopLink.size(); i++){
                String[] parser = stopLink.eq(i).toString().split("\"");
                if(i == 0){
                    //Do nothing because this link takes you back to route list
                }
                else{
                    String[] parsed = parser[1].split("/");
                    stopList.add("/" + parsed[5]);
                }
            }

            Bus newBus = new Bus();
            Boolean addBus = false;
            //Combines stop links and scrapes info from the pages
            for(int i = 0; i < stopList.size(); i++){
                String fullStopLink = fullLink + stopList.get(i);
                doc = Jsoup.connect(fullStopLink).get();
                Elements listElements = doc.getElementsByTag("li");
                Stop newStop = new Stop();

                //Parses estimated stop times from the page and stores it in stop object
                for(int ind = 1; ind < listElements.size(); ind++){
                    Integer arrivalTimeMin = 0;
                    String[] parser = listElements.eq(ind).toString().split(" ");

                    //Handles edge case when the ETA is low due to the bus arriving.
                    //Arrival will be set to 0 but should be presented to user as "soon".
                    if(parser[4].equals("arriving.")){
                        arrivalTimeMin = 0;
                        addBus = true;
                        newStop.addPair(newBus, arrivalTimeMin);
                    }
                    else if(parser[4].equals("available")){
                        arrivalTimeMin = -1;
                        addBus = false;
                    }
                    else{
                        arrivalTimeMin = Integer.parseInt(parser[4]);
                        addBus = true;
                        newStop.addPair(newBus, arrivalTimeMin);
                    }
                }
                if(addBus){
                    stops.add(newStop);
                    newBus.addStop(newStop);
                    buses.add(newBus);
                }
            }


        }

    }

    public void printSchedule(){
        for(int index = 0; index < buses.size(); index++){
            Bus bus = buses.get(index);
            ArrayList<Stop> stops = bus.getStops();
            for(int i = 0; i < stops.size(); i++){
                Stop stop = stops.get(i);
                Pair<Bus, Integer> pair = stop.getArrival();
                System.out.println("Bus will arrive in: " + pair.time + " minutes.");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ShuttleScraper s = new ShuttleScraper();
        s.scrape();
        s.printSchedule();

//        String mainPage = "https://ucsdbus.com/simple/routes/";
//        String gilmanMyers = "2092/stops/34893";
//        Document doc = Jsoup.connect(mainPage + gilmanMyers).get();
//        Elements listElements = doc.getElementsByTag("li");
//        int size = listElements.size();
//        ArrayList<String> times = new ArrayList<String>();
//        for (int i=1; i < size; i++) {
//            String[] parser = listElements.eq(i).toString().split(" ");
//            int arrivalTimeMin = Integer.parseInt(parser[4]);
//            //Edge Case: Bus is currently arriving.
//            if(parser[2].equals("is")){
//                System.out.println("Next bus is arriving soon.");
//                arrivalTimeMin = 0; //0 should be considered as and displayed to the user as "soon".
//            }
//            //Edge Case: Bus is not running or no accurate arrival time estimations exist.
//            else if(parser[2].equals("predictions")){
//
//            }
//            else{
//                arrivalTimeMin = Integer.parseInt(parser[4]);
//            }

//            String[] timeParser = parser[7].split(":");
//            int hour = Integer.parseInt(timeParser[0]);
//            int min = Integer.parseInt(timeParser[1]);
//            if(parser[8].equals("PM.")){
//                hour += 12;
//                hour = hour % 24;
//            }
        //String newTime = "" + hour + ':' + min + ':' + "00";
        //DateFormat sdf = new SimpleDateFormat(newTime);
        //Date busDate = new Date();
        //busDate.
        //Date busDate = sdf.parse(newTime);
        //Date currDate = new Date();

        //times.add(parsed);
        //System.out.println("Time until bus arrives: " + arrivalTimeMin + " Minutes.");

        //}


    }
}
