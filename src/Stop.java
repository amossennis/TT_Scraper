import java.util.*;
public class Stop {
    private final PriorityQueue<Pair<Bus, Integer>> queue;
    private ArrayList<Bus> buses;
    //private final int geolocation;
    //private final int oneBusAwayID;


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
    public ArrayList<Bus> getBuses(){return this.buses;}
    public Pair<Bus, Integer> getArrival(){
        Pair<Bus, Integer> pair = queue.peek();
        queue.remove(pair);
        return pair;
    }
    public Boolean stopsAt(Bus bus){
        if(buses.indexOf(bus) == -1) {
            return false;
        }else{
            return true;
        }
    };
}

