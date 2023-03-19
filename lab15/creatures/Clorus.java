package creatures;

import huglife.*;

import java.awt.Color;
import java.nio.channels.Pipe;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    private final int r = 34;
    private final int g = 0;
    private final int b = 231;
    private final double repEnergyRetained = 0.5;
    private final double repEnergyGiven = 0.5;

    /**
     * Creates a
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        double childEnergy = energy * repEnergyGiven;
        energy *= repEnergyRetained;
        return new Clorus(childEnergy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (plips.size() > 0) {
            Direction attackDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, attackDir);
        }

        if (energy >= 1.0) {
            Direction replicationDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, replicationDir);
        }

        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
