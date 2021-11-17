import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.ScreenInfoAction;
import com.googlecode.lanterna.screen.Screen;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    Hero hero = new Hero(new Position(10,10),"X","BLUE");

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public boolean canHeroMove(Position position){
        for(Wall wall: walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }

    public boolean moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            if(!verifyMonsterCollisions()){
                return false;
            }
            retrieveCoins();
            if(coins.size()==0){
                System.out.println("YOU WON!");
                return false;
            }
            moveMonsters();
            if(!verifyMonsterCollisions()){
                return false;
            }
        }
        return true;
    }

    public void draw(TextGraphics graphics){
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
    }

    public boolean processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()){
            case ArrowUp:
                if(!moveHero(hero.moveUp())){
                    return false;
                }
                return true;
            case ArrowDown:
                if(!moveHero(hero.moveDown())){
                    return false;
                }
                return true;
            case ArrowLeft:
                if(!moveHero(hero.moveLeft())){
                    return false;
                }
                return true;
            case ArrowRight:
                if(!moveHero(hero.moveRight())){
                    return false;
                }
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

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0),"*","WHITE"));
            walls.add(new Wall(new Position(c, height - 1),"*","WHITE"));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(new Position(0,r),"*","WHITE"));
            walls.add(new Wall(new Position(width - 1, r),"*","WHITE"));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1),"0","YELLOW"));
        return coins;
    }

    private void retrieveCoins(){
        for (Coin coin : coins)
            if (coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            monsters.add(new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1),"M","RED"));
        return monsters;
    }

    private boolean canMonsterMove(Position position){
        for(Wall wall: walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }
        for(Monster monster: monsters){
            if (monster.getPosition().equals(position)){
                return false;
            }
        }
        for(Coin coin: coins){
            if (coin.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }

    public void moveMonsters(){
        for(Monster monster:monsters){
            Position temp = monster.move();
            if (canMonsterMove(temp)){
                monster.setPosition(temp);
            }
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster:monsters){
            if(hero.getPosition().equals(monster.getPosition())){
                System.out.println("YOU LOST!");
                return false;
            }
        }
        return true;
    }
}
