package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

import javax.sound.midi.Soundbank;

/** Tests the plip class   
 *  @authr FIXME
 */

public class TestPlip {

    /* Replace with the magic word given in lab.
     * If you are submitting early, just put in "early" */
    public static final String MAGIC_WORD = "";

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Plip parent = new Plip(2);
        Plip child = parent.replicate();
        double delta = 0.00001;
        assertEquals(parent.energy() * 2.0, 2.0, delta);
        assertEquals(child.energy() * 2.0, 2.0, delta);
        assertNotSame(parent, child);
    }

    @Test
    public void testChoose() {
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!  

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // more with Cloruses
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Clorus(2.0));
        surrounded.put(Direction.RIGHT, new Clorus(2.0));

        actual = p.chooseAction(surrounded);
        Action expected01 = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        Action expected02 = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertTrue(
            actual.equals(expected01) || actual.equals(expected02)
        );

        System.out.println(p.energy());
        p.replicate();
        System.out.println(p.energy());
        Action expected1 = new Action(Action.ActionType.MOVE, Direction.TOP);
        Action expected2 = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        Action expected3 = new Action(Action.ActionType.STAY);
        actual = p.chooseAction(surrounded);
        assertTrue(
            actual.equals(expected1) || actual.equals(expected2) || actual.equals(expected3)
        );
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
} 
