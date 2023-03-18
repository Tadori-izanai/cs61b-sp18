package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    protected final int period;
    protected int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    protected double normalize(int val) {
//        return 2.0 / period * val - 1.0;
        return 2.0 / (period - 1) * val - 1.0;
    }

    @Override
    public double next() {
        state += 1;
        return normalize(state % period);
    }
}
