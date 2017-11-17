import java.util.*;
public class Bus {
    private ArrayList<Stop> stops;

    public void addStop(Stop stop){
        stops.add(stop);
    }
    public Boolean stopAt(Stop stop){
        if(stops.indexOf(stop) == -1) {
            return false;
        }else{
            return true;
        }
    }
}
