package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private final double factor;
    private int state;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
        state = 0;
    }

    private double normalize(int val) {
        return 2.0 / (period - 1) * val - 1.0;
    }

    @Override
    public double next() {
        state += 1;
        if (state == period) {
            state = 0;
            period = (int) (period * factor);
        }
        return normalize(state);
    }
}
