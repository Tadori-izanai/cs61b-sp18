package lab14;

import lab14lib.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Generator generator = new SineWaveGenerator(200);

//		GeneratorPlayer gp = new GeneratorPlayer(generator);
//		gp.play(1_000_000);		// plays first 1 million samples

//		GeneratorDrawer gd = new GeneratorDrawer(generator);
//		gd.draw(4690);			// draws the first 4096 samples

//        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
//        gav.drawAndPlay(4096, 1_000_000);

//        GeneratorAudioAnimator gaa = new GeneratorAudioAnimator(generator);
//        gaa.drawAndPlay(4096, 1_000_000);


//        beatDemo();
//        testSawTooth();
//        sineAndSawTooth();
//        testAcceleratingSawTooth();
//        testStrangeBitwiseGenerator();
        ps();
    }

    public static void beatDemo() {
        Generator g1 = new SineWaveGenerator(60);
        Generator g2 = new SineWaveGenerator(61);
        ArrayList<Generator> generators = new ArrayList<>();
        generators.add(g1);
        generators.add(g2);
        MultiGenerator mg = new MultiGenerator(generators);

        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(mg);
        gav.drawAndPlay(500_000, 1_000_000);
    }

    public static void testSawTooth() {
        Generator g = new SawToothGenerator(512);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(g);
        gav.drawAndPlay(4096, 1_000_000);
    }

    public static void sineAndSawTooth() {
        Generator gSine = new SineWaveGenerator(440);
        Generator gSaw = new SawToothGenerator(512);
        ArrayList<Generator> generators = new ArrayList<>();
        generators.add(gSine);
        generators.add(gSaw);
        MultiGenerator mg = new MultiGenerator(generators);

        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(mg);
        gav.drawAndPlay(4096, 1_000_000);
    }

    public static void testAcceleratingSawTooth() {
        Generator g = new AcceleratingSawToothGenerator(200, 0.99);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(g);
        gav.drawAndPlay(4096, 1_000_000);
    }

    public static void testStrangeBitwiseGenerator() {
        Generator g = new StrangeBitwiseGenerator(512);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(g);
        gav.drawAndPlay(4096, 1_000_000);
    }

    public static void ps() {
        Generator generator = new StrangeBitwiseGenerator(1024);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(128_000, 1_000_000);
    }
}
