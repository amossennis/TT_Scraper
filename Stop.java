import java.util.*;
public class Stop {
    private final Queue<Pair<Bus, Integer>> queue;
    private ArrayList<Bus> buses;
    //private final int geolocation;
    //private final int oneBusAwayID;

    private class Pair <Bus, Integer> {
        public final Bus bus;
        public final Integer time;
        public Pair(Bus bus, Integer time){
            this.bus = bus;
            this.time = time;
        }
    }

    public Stop(Queue<Pair<Bus, Integer>> queue) {
        this.queue = queue;
    }

    public final Queue<Pair<Bus, Integer>> getStopTimes(){
        return queue;
    }
    public void addPair(Bus bus, Integer time) {this.queue.add(new Pair(bus, time));}
    public void addBus(Bus bus){buses.add(bus);}
    public Boolean stopAt(Bus bus){
        if(buses.indexOf(bus) == -1) {
            return false;
        }else{
            return true;
        }
    };
}

