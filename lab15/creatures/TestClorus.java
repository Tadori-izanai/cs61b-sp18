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

public class TestClorus {
    @Test
    public void testBasics() {
        double delta = 0.0001;
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), delta);

        c.move();
        assertEquals(1.97, c.energy(), delta);

        c.move();
        assertEquals(1.94, c.energy(), delta);

        c.stay();
        assertEquals(1.93, c.energy(), delta);

        c.stay();
        assertEquals(1.92, c.energy(), delta);
    }

    @Test
    public void testReplicate() {
        Clorus parent = new Clorus(2.0);
        Clorus child = parent.replicate();
        double delta = 0.0001;
        assertEquals(parent.energy() * 2.0, 2.0, delta);
        assertEquals(child.energy() * 2.0, 2.0, delta);
        assertNotSame(parent, child);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(2.0);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.LEFT, new Plip());
        surrounded.put(Direction.BOTTOM, new Plip());
        surrounded.put(Direction.RIGHT, new Plip());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(actual, expected);

        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.TOP, new Impassible());
        Action expected1 = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        Action expected2 = new Action(Action.ActionType.REPLICATE, Direction.LEFT);
        actual = c.chooseAction(surrounded);
        assertTrue(
            actual.equals(expected1) || actual.equals(expected2)
        );
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestClorus.class));

    }
}
