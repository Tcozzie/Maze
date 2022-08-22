/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author tiegancozzie
 */
public class Maze  {

    String maze[][];
    int width, height;
    Scanner pause;
    int playerx,playery;
    int handx,handy;
    String direction;
    int playerStartx,playerStarty;
    
    Maze(){
        pause=new Scanner(System.in);
        Scanner s = null;
        Scanner r= null;
        int temp=1;
        try{
            r=new Scanner (new File("maze1.txt")); //Change file name to desired maze |  Maze files have to be inside project folder
            s=new Scanner (new File("maze1.txt")); //Change file name to desired maze |  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
            System.exit(0);
        }
        
        width=s.nextLine().length()/2;
        while(s.hasNextLine()){
            temp+=1;
            s.nextLine();
        }
        height=temp;
        maze=new String[height][width];

        
        for(int height=0;height<maze.length;height++){
            for(int width=0;width<maze[height].length;width++){
                maze[height][width]=r.next();

            }
        }
        
    }
    
    public void play(){
        addX();
        print();
        move(playerx,playery,handx,handy);
    }
    
    public void print(){
        for(int i=0;i<maze.length;i++){
            for(int x=0;x<maze[i].length;x++){
                System.out.print(maze[i][x]+" ");
            }
            System.out.println();
        }
        System.out.println("Player Loc: ("+playery+","+playerx+")");
        System.out.println("Hand Loc: ("+handy+","+handx+")");
        System.out.println("Direction: "+direction);
    }
    
    public void addX(){
        for(var height=0;height<maze.length;height++){
            for(var width=0;width<maze[height].length;width++){
                if(((height==0||width==0)||(height==maze.length-1||width==maze[height].length-1))&&maze[height][width].equals(".")){
                    maze[height][width]="X";
                    playerx=width;
                    playery=height;
                    playerStartx=width;
                    playerStarty=height;
                    if(height==0){
                        handx=width-1;
                        handy=height;
                        direction="South";
                    }else if(width==0){
                        handx=width;
                        handy=height+1;
                        direction="East";
                    }else if(height==maze.length-1){
                        handx=width+1;
                        handy=height;
                        direction="North";
                    }else if(width==maze[height].length-1){
                        handx=width;
                        handy=height-1;
                        direction="West";
                    }
                }
            }
        }
    }
    
    public String lookAhead(String x){
        String ahead = null;
        if(x.equals("North")){
            ahead=maze[playery-1][playerx];
        }else if(x.equals("East")){
            ahead=maze[playery][playerx+1];
        }else if(x.equals("South")){
            ahead=maze[playery+1][playerx];
        }else if(x.equals("West")){
            ahead=maze[playery][playerx-1];          
        }
        return ahead;
    }
    
    
    public void move(int playerColumn, int playerRow, int handColumn, int handRow){
        String front;
        String hand=maze[handy][handx];
        pause.nextLine(); // Remove this line if you want computer to run maze quickly
        if(maze[playery][playerx].equals("W")){
            System.out.println();
            System.out.println("You Win!!");
            System.exit(0);
        }else{
            switch(direction){
                    case "North":
                        front=lookAhead(direction);
                        if(hand.equals(".") || hand.equals("X")){
                            playerx+=1;
                            direction="East";
                            handy+=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals(".") || front.equals("X")){
                            playery-=1;
                            handy-=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals("#") && hand.equals("#")){
                            direction="West";
                            handx=playerx;
                            handy=playery-1;
                        }else if(front.equals("F")){
                            playery-=1;
                            handy-=1;
                            maze[playery][playerx]="W";
                        }else if(hand.equals("F")){
                            playerx+=1;
                            handy+=1;
                            direction="East";
                            maze[playery][playerx]="W";
                        }
                        print();
                        break;
                    case "East":
                        front = lookAhead(direction);
                        if(hand.equals(".") || hand.equals("X")){
                            playery+=1;
                            direction="South";
                            handx-=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals(".") || front.equals("X")){
                            playerx+=1;
                            handx+=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals("#") && hand.equals("#")){
                            direction="North";
                            handy=playery;
                            handx=playerx+1;
                        }else if(front.equals("F")){
                            playerx+=1;
                            handx+=1;
                            maze[playery][playerx]="W";
                        }else if(hand.equals("F")){
                            playery+=1;
                            direction="South";
                            handx-=1;
                            maze[playery][playerx]="W";
                        }
                        print();
                        break;

                    case "South":
                        front=lookAhead(direction);
                        if(hand.equals(".") || hand.equals("X")){
                            playerx-=1;
                            direction="West";
                            handy-=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals(".") || front.equals("X")){
                            playery+=1;
                            handy+=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals("#") && hand.equals("#")){
                            direction="East";
                            handx=playerx;
                            handy=playery+1;
                        }else if(front.equals("F")){
                            playery+=1;
                            handy+=1;
                            maze[playery][playerx]="W";
                        }else if(hand.equals("F")){
                            playerx-=1;
                            direction="West";
                            handy-=1;
                            maze[playery][playerx]="W";
                        }
                        print();
                        break;
                    case "West":
                        front=lookAhead(direction);
                        if(hand.equals(".") || hand.equals("X")){
                            playery-=1;
                            direction="North";
                            handx+=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals(".") || front.equals("X")){
                            playerx-=1;
                            handx-=1;
                            maze[playery][playerx]="X";
                        }else if(front.equals("#") && hand.equals("#")){
                            direction="South";
                            handy=playery;
                            handx-=1;
                        }else if(front.equals("F")){
                            playerx-=1;
                            handx-=1;
                            maze[playery][playerx]="W";
                        }else if(hand.equals("F")){
                            playery-=1;
                            direction="North";
                            handx+=1;
                            maze[playery][playerx]="W";
                        }
                        print();
                        break;
            }
            if(playerx==playerStartx && playery==playerStarty){
                System.out.println();
                System.out.println("Impossible Maze!");
                System.exit(0);
            }
            move(playerx,playery,handx,handy);
        }
    }

    
    public static void main(String[] args) {
       new Maze().play();
    }
    
}
