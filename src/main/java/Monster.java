import java.util.Random;

public class Monster extends Element{

    int randomPosition = 0;
    int randomPosition_2=0;

    public Monster(Position pos, String s, String clr) {
        super(pos, s, clr);
    }

    public Position move(){
        Random random = new Random();
        Random random_2 = new Random();
        randomPosition = random.nextInt(3)-1;
        randomPosition_2 = random_2.nextInt(3)-1;
        return new Position(getPosition().getX()+randomPosition, getPosition().getY()+randomPosition_2);
    }

}
