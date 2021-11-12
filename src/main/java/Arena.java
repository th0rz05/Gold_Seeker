import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.ScreenInfoAction;
import com.googlecode.lanterna.screen.Screen;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;

    Hero hero = new Hero(new Position(10,10));

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }
    public boolean canHeroMove(Position position){

        if(position.getX() < width  && position.getX() >= 0 &&
                position.getY() < height  && position.getY() >= 0){
            return true;
        }
        return false;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);

        }
    }

    public void draw(TextGraphics graphics){
        hero.draw(graphics);
    }

    public boolean processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()){
            case ArrowUp:
                moveHero(hero.moveUp());
                return true;
            case ArrowDown:
                moveHero(hero.moveDown());
                return true;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                return true;
            case ArrowRight:
                moveHero(hero.moveRight());
                return true;
            case Character:
                if (key.getCharacter()=='q'){
                    return false;
                }
                return true;
            case EOF:
                return false;
        }
        return true;
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
