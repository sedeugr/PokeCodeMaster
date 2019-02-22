package org.academiadecodigo.bootcamp.Game;

import org.academiadecodigo.bootcamp.Game.Pokemons.PokeList;
import org.academiadecodigo.bootcamp.Game.Pokemons.Pokes;
import org.academiadecodigo.bootcamp.Game.StagesPack.Attic;
import org.academiadecodigo.bootcamp.Game.StagesPack.Base;
import org.academiadecodigo.bootcamp.Game.StagesPack.Stages;
import org.academiadecodigo.bootcamp.Game.StagesPack.Unicornios;
import org.academiadecodigo.bootcamp.Game.UtilitiesPack.Copa;
import org.academiadecodigo.bootcamp.Game.UtilitiesPack.PingPong;
import org.academiadecodigo.bootcamp.Game.UtilitiesPack.Utilities;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 19/02/2019.
 *
 * Instantiate all the backgrounds and shows them in a switch case with the 1,2,3,4,5,6 keys
 *
 *
 */
public class Corridor implements MouseHandler {

    //Instantiate backgrounds
    private Stages attic = new Attic();
    private Stages base = new Base();
    private Stages unicornios = new Unicornios();
    private Utilities pingPong = new PingPong();
    private Utilities copa = new Copa();
    private Utilities varanda = new Varanda();
    PlayerScreen playerScreen;
    private GameScreens stages;
    Rectangle highligth = new Rectangle(40,180,100,100);;
    private PokeList pokeList = new PokeList();


    private Picture corridor1 = new Picture(10,10,"corr1.jpg");
    private Picture corridor2 = new Picture(10,10,"corr2.jpg");
    private Picture corridor3 = new Picture(10,10,"corr3.jpg");

    private boolean stageIsOngoing=false;

    //chooses witch corridor to show

    public Corridor() throws InterruptedException{


        corridor3.draw();
        boolean bool;



        Mouse m = new Mouse(this);
        m.addEventListener(MouseEventType.MOUSE_CLICKED);

        m.addEventListener(MouseEventType.MOUSE_MOVED);


        while (true) {
            System.out.print("");
            if (stageIsOngoing) {
                if(stages instanceof Stages) {
                    stages.drawStage();
                    playerScreen = new PlayerScreen();
                    bool = playerScreen.init(base.startPokemon());
                    if (bool) {
                        stages.hideStage();
                    }
                    stageIsOngoing = false;
                }else{
                    stages.drawStage();
                    stages.hideStage();
                    stageIsOngoing = false;

                }
            }
        }

    }

    public Pokes[] getList(){
        Pokes[] list;
        if (stages == base){

        }
        if (stages == attic){

        }
        if (stages == unicornios){
            list = new Pokes[2];
            for (int i = 7; i < 9; i++)
                list[i] = pokeList.getElement(i);
        }
        else{
            System.out.println("Something wrong on list.");
            list = new Pokes[0];
        }
        return list;
    }

    public GameScreens getStages(){return stages;}

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getY());

        if(mouseEvent.getY()>190 && mouseEvent.getY()<270){
            //Go to base
            stages=base;
            stageIsOngoing=true;
        }
        if(mouseEvent.getY()>270 && mouseEvent.getY()<350){
            //Go to attic
            stages=attic;
            stageIsOngoing=true;

        }
        if((mouseEvent.getY()>350 && mouseEvent.getY()<420)&& pingPong.isUnlocked()){
            //Go to pingpong
            stages=pingPong;
            stageIsOngoing=true;
        }
        if(mouseEvent.getY()>420 && mouseEvent.getY()<490 && copa.isUnlocked()){
            //Go to copa
            stages=copa;
            stageIsOngoing=true;
        }
        if(mouseEvent.getY()>490 && mouseEvent.getY()<560 && varanda.isUnlocked()){
            //Go to varanda
            stages=varanda;
            stageIsOngoing=true;
        }
        if(mouseEvent.getY()>560 && mouseEvent.getY()<624 && unicornios.isUnlocked()){
            //Go to unicornios
            stages=unicornios;
            stageIsOngoing=true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if(mouseEvent.getY()>190 && mouseEvent.getY()<270 && !stageIsOngoing){
            highligth.draw();
        }else{
            highligth.delete();
        }

    }
}
