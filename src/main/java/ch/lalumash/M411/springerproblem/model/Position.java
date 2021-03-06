package ch.lalumash.M411.springerproblem.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a position  on a Board.
 */
@Getter
public class Position implements Cloneable {
    private int x;
    private int y;
    private transient Board board;
    @Getter(AccessLevel.PRIVATE)
    private transient List<Position> positions;

    /**
     * @param x the positions x value.
     * @param y the positions x value.
     * @param board the board where the position is located.
     */
    public Position(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    /**
     * this Method is used to get all Positions from the current position.
     * This Method caches the positions once they are created.
     *
     * @return the List of all Position on the Board that the knight can move to.
     */
    public List<Position> getPossiblePositions() {
        if (positions == null) {
            List<Position> result = new ArrayList<Position>();

            for (int i = 0; i < Move.moves.length; i++) {
                int x = this.getX() + Move.moves[i].getX();
                int y = this.getY() + Move.moves[i].getY();

                if (x >= 0 && y >= 0
                        && x < board.getDimensions()
                        && y < board.getDimensions()) {
                    result.add(board.get(x, y));
                }
            }
            positions = result;
        }

        return positions;
    }

    @Override
    public Position clone() {
        return new Position(this.getX(), this.getY(), this.board);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) {
            return false;
        }
        Position pos = (Position) other;

        return pos.getY() == this.getY() && pos.getX() == this.getX();
    }

    @Override
    public String toString() {
        return "[" + this.getX() + ";" + this.getY() + "]";
    }
}
