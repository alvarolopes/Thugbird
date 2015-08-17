package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import dinamo.thugbird.grafics.Screen;

public class Elements {

    private static final int QUANTITY_OF_ELEMENTS = 5;
    private static final int DISTANCE_BETWEEN_PIPES = 250;
    private final Random rand;

    private final List<Police> cops = new ArrayList<>();
    private final List<Money> prizes = new ArrayList<>();
    private final List<Background> bgs = new ArrayList<>();
    private Screen screen;

    public Elements(Screen screen, Context context) {
        this.screen = screen;
        int initialPosition = screen.getWidth();
        rand = new Random();

        for(int i=0; i < QUANTITY_OF_ELEMENTS; i++) {
            initialPosition += DISTANCE_BETWEEN_PIPES;


            cops.add(new Police(getRandomPoliceTop(), initialPosition, context, screen));
            prizes.add(new Money(getRandomMoneyTop(), initialPosition, context, screen));
        }

        Background bg1 = new Background(0,screen,context);
        bgs.add(bg1);
        Background bg2 = new Background(getLatestBgRight(),screen,context);
        bgs.add(bg2);

    }

    public void drawAt(Canvas canvas) {
        for (Background bg : bgs){
            bg.drawAt(canvas);
        }

        for (Money money : prizes) {
            money.drawAt(canvas);
        }

        for (Police police : cops) {
            police.drawAt(canvas);
        }
    }

    public void move() {

        for (Background bg : bgs) {
            bg.move();
            if (bg.isOutOfScreen()) {
                bg.setPosition(getLatestBgRight());
            }
        }

        for (Money money : prizes) {
            money.move();
            if (money.isGet() || money.isOutOfScreen()) {
                money.reset(getRandomMoneyTop(), getLatestMoneyRight());
            }
        }

        for (Police police : cops) {
            police.move();
            if (police.isOutOfScreen()) {
                police.reset(getRandomPoliceTop(), getLatestElementRight());
            }
        }
    }

    private int getLatestBgRight() {
        int maximo = 0;
        for(Background bg : bgs) {
            maximo = Math.max(bg.getRight(), maximo);
        }

        return maximo;
    }

    private int getLatestMoneyRight() {
        int maximo = 0;
        for(Money money : prizes) {
            maximo = Math.max(money.getRight(0), maximo);
        }

        if (maximo < screen.getWidth())
            return screen.getWidth();

        return maximo+DISTANCE_BETWEEN_PIPES;
    }

    private int getLatestElementRight() {
        int maximo = 0;
        for(Police police : cops) {
            maximo = Math.max(police.getRight(0), maximo);
        }

        if (maximo < screen.getWidth())
            return screen.getWidth();

        return maximo+DISTANCE_BETWEEN_PIPES;
    }

    private int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    private int getRandomPoliceTop() {

        int policeHeight;
        int random = screen.getHeight()/2;

        if (!cops.isEmpty()){
            policeHeight = cops.get(0).getHeight();
            random = randInt((screen.getHeight()*11/100) +policeHeight, screen.getHeight()-(screen.getHeight()*11/100)-policeHeight);
        }

        for (Police cop : cops) {
            if (cop.hasHorizontalCollision(random, random + cops.get(0).getHeight()))
                random = getRandomPoliceTop();
        }

        return random;
    }

    public boolean hasBumpWith(Bird bird) {
        for (Police police : cops) {
            if (police.hasCollision(bird, 1, 10)) {
                return true;
            }
        }
        return false;
    }

    public boolean gotPrize(Bird bird) {
        for (Money prize : prizes) {
            if (prize.hasCollision(bird, 0, 0)) {
                prize.Get();
                return true;
            }
        }
        return false;
    }

    private int getRandomMoneyTop() {
        return randInt((screen.getHeight()*13/100), screen.getHeight()-(screen.getHeight()*13/100)-Money.HEIGHT);
    }

    public boolean isOutOfTheRoad(Bird bird) {
        return bird.getTop(2) < (screen.getHeight() * 5 / 100) ? true : bird.getBottom(2) > screen.getHeight() - (screen.getHeight() * 5 / 100);

    }
}
