import java.util.*;
public class Stop {
    private final PriorityQueue<Pair<Bus, Integer>> queue;
    private ArrayList<Bus> buses;
    //private final int geolocation;
    //private final int oneBusAwayID;

    public class Pair <Bus, Integer> {
        public Bus bus;
        public Integer time;
        public Pair(){
            this.bus = null;
            this.time = null;
        }
        public Pair(Bus bus, Integer time){
            this.bus = bus;
            this.time = time;
        }

        public void setBus(Bus b){
            this.bus = b;
        }

        public void setTime(Integer t){
            this.time = t;
        }
    }


    private class PairComparator implements Comparator<Pair<Bus, Integer>>{
        public int compare(Pair<Bus, Integer> pair1, Pair<Bus, Integer> pair2) {
            return pair1.time.compareTo(pair2.time);
        }
    }

    public Stop(){
        this.queue = new PriorityQueue<Pair<Bus, Integer>>(15, new PairComparator());
    }
    public Stop(PriorityQueue<Pair<Bus, Integer>> queue) {
        this.queue = queue;
    }

    public final PriorityQueue<Pair<Bus, Integer>> getStopTimes(){
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

