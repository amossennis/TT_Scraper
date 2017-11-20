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
