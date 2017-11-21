import java.util.*;
public class Bus {
    private ArrayList<Stop> stops;
    public String tag;

    public Bus(){
        this.stops = new ArrayList<Stop>();
        this.tag = "";
    }
    public Bus(String tag){
        this.stops = new ArrayList<Stop>();
        this.tag = tag;
    }
    public void addStop(Stop stop){
        stops.add(stop);
    }
    public ArrayList<Stop> getStops(){ return this.stops; }
    public String getTag(){ return this.tag; }
    public Boolean stopAt(Stop stop){
        if(stops.indexOf(stop) == -1) {
            return false;
        }else{
            return true;
        }
    }
}
