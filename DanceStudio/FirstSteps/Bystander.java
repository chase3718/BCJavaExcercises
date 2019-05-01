// This class represents a Bystander with two feet.

import java.awt.Image;
import java.awt.Graphics;

public class Bystander
{
  public static final int PIXELS_PER_INCH = 6;
  private Foot leftFoot, rightFoot;
  private int stepLength;
  private int stepsCount;

  // Constructor
  public Bystander(int x, int y, Image leftPic, Image rightPic)
  {
    leftFoot =  new Foot(x, y - PIXELS_PER_INCH * 4, leftPic);
    rightFoot = new Foot(x, y + PIXELS_PER_INCH * 4, rightPic);
    stepLength = PIXELS_PER_INCH * 12;
  }

  // Returns the left foot
  public Foot getLeftFoot()
  {
    return leftFoot;
  }

  // Returns the right foot
  public Foot getRightFoot()
  {
    return rightFoot;
  }

  // Makes first step, starting with the left foot
  public void firstStep()
  {
	//leftFoot.turn(-45);
    stepsCount = 1;
  }
  public void hop(){
  	leftFoot.moveForward(stepLength);
  	rightFoot.moveForward(stepLength);
  }
  // Makes next step
  int lt = -1;
  int rt = 1;
  public void nextStep()
  {
    if (stepsCount % 4 == 0){  // if stepsCount is even
      	rightFoot.turn(-45);
    } else if (stepsCount % 4 == 3) {
    	rightFoot.turn(45);
    }else if (stepsCount % 4 == 1){
      	leftFoot.turn(-45);
    }else{
    	leftFoot.turn(45);
    }
  stepsCount++;
  }
  // Stops this Bystander (brings its feet together)
  public void stop()
  {

    if(stepsCount % 2 == 0){

    }else{

    }

  }
  public void stopHop(){

  }
  // Returns the distance walked
  public int distanceTraveled()
  {
    return stepsCount * stepLength;
  }

  // Draws this Bystander
  public void draw(Graphics g)
  {
    leftFoot.draw(g);
    rightFoot.draw(g);
  }
}
