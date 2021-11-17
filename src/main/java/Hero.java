import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

    public Hero(Position pos, String s, String clr) {
        super(pos, s, clr);
    }

    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public Position moveLeft(){
        return new Position(getPosition().getX()-1, getPosition().getY());
    }

    public Position moveRight(){
        return new Position(getPosition().getX()+1, getPosition().getY());
    }

}

