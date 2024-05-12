package main.resources.map;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import main.resources.map.RobotDir.Direction;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.ApplAbstractObserver;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;
import unibo.basicomm23.ws.WsConnection;

/**
 * Updates the position of the robot on the map based on the movements it makes
 * by observing the robot movements
 * 
 * @author Luca Corsetti <https://github.com/ilcors-dev>
 */
public class MovementTracker extends ApplAbstractObserver {
    protected String vitualRobotIp = "localhost";
    protected Interaction conn;
    protected RoomMap roomMap;
    private int posX, posY;

    // keep track of the last directions the robot moved in, by doing so we can
    // reconstruct the map of the position from
    // the movements tracked by observing the robot
    private List<RobotDir.Direction> lastDirections;

    public MovementTracker(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;

        this.conn = ConnectionFactory.createClientSupport(ProtocolType.ws, vitualRobotIp + ":8091", "");

        CommUtils.outcyan("MovementTracker created, connecting to " + vitualRobotIp + ":8091");

        ((WsConnection) conn).addObserver(this);

        RobotDir.setDir(Direction.DOWN);
        CommUtils.outcyan("RobotDir set to DOWN");

        roomMap = RoomMap.loadRoomMap("map");
        roomMap.clear();
        roomMap.setPos(posX, posY, RoomMap.cellvalue.ROBOT);
        roomMap.setCellClean(posX, posY);
        roomMap.setRobot(posX, posY);
        roomMap.showMap();

        lastDirections = new ArrayList<Direction>();
        lastDirections.add(Direction.DOWN);
    }

    /**
     * Get the current position of the robot
     * 
     * @return an array containing the x and y coordinates of the robot
     */
    public int[] getPos() {
        return new int[] { posX, posY };
    }

    public void showMap() {
        roomMap.showMap();
    }

    public void saveRoomMap(String fname) {
        try {
            roomMap.saveRoomMap(fname, roomMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkMoveResult(JSONObject json) {
        boolean moveResult = json.get("endmove").toString().contains("true");
        return moveResult;
    }

    private void updatePos() {
        if (RobotDir.goingUp()) {
            posY--;
        } else if (RobotDir.goingDown()) {
            posY++;
        } else if (RobotDir.goingLeft()) {
            posX--;
        } else if (RobotDir.goingRight()) {
            posX++;
        }

        CommUtils.outcyan("Robot moved to: " + posX + ", " + posY);
    }

    @Override
    public void update(String value) {
        CommUtils.outcyan("MovementTracker received: " + value);

        JSONObject json = CommUtils.parseForJson(value);

        if (json == null) {
            CommUtils.outred("MovementTracker received invalid JSON");
        }

        if (json.get("endmove") == null) {
            return;
        }

        String move = json.get("move").toString();
        boolean moveResult = checkMoveResult(json);

        RobotDir.Direction lastDir = lastDirections.get(lastDirections.size() - 1);
        RobotDir.Direction newDirection = lastDir;

        switch (move) {
            // we only update the position of the robot if the move was successful
            // or it either reached the end of the room x-wise because it may have collided
            // and reached the end of the room
            case "moveBackward":
                if (moveResult || lastDir == RobotDir.Direction.RIGHT || lastDir == RobotDir.Direction.LEFT) {
                    roomMap.setCellClean(posY, posX);
                    roomMap.showMap();
                }
                break;
            case "moveForward":
            case "moveForward-collision":
                // we only update the position of the robot if the move was successful
                // or it either reached the end of the room x-wise because it may have collided
                // and reached the end of the room
                if (moveResult || lastDir == RobotDir.Direction.RIGHT || lastDir == RobotDir.Direction.LEFT) {
                    this.updatePos();
                    roomMap.setCellClean(posY, posX);
                    roomMap.showMap();
                }
                break;
            case "turnLeft":
                // different scenarios based on the last direction the robot moved in
                if (lastDir == RobotDir.Direction.DOWN) {
                    newDirection = RobotDir.Direction.RIGHT;
                } else if (lastDir == RobotDir.Direction.UP) {
                    newDirection = RobotDir.Direction.LEFT;
                } else if (lastDir == RobotDir.Direction.LEFT) {
                    newDirection = RobotDir.Direction.DOWN;
                } else if (lastDir == RobotDir.Direction.RIGHT) {
                    newDirection = RobotDir.Direction.UP;
                }

                break;
            case "turnRight":
                // different scenarios based on the last direction the robot moved in
                if (lastDir == RobotDir.Direction.DOWN) {
                    newDirection = RobotDir.Direction.LEFT;
                } else if (lastDir == RobotDir.Direction.UP) {
                    newDirection = RobotDir.Direction.RIGHT;
                } else if (lastDir == RobotDir.Direction.LEFT) {
                    newDirection = RobotDir.Direction.UP;
                } else if (lastDir == RobotDir.Direction.RIGHT) {
                    newDirection = RobotDir.Direction.DOWN;
                }

                break;

            // we do not care about the other moves that may be sent by the robot at the
            // moment
            default:
                CommUtils.outcyan("MovementTracker ignoring move: " + move);
                break;
        }

        CommUtils.outcyan("RobotDir set to: " + newDirection);
        RobotDir.setDir(newDirection);
        lastDirections.add(newDirection);

        return;
    }
}
