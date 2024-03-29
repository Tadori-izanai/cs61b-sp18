package lab14;

public class StrangeBitwiseGenerator extends SawToothGenerator {
    public StrangeBitwiseGenerator(int period) {
        super(period);
    }

    @Override
    public double next() {
        state += 1;
//        int weirdState = state & (state >>> 3) % period;
//        int weirdState = state & (state >> 3) & (state >> 8) % period;
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState);
    }
}
