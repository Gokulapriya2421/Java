/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author PriyaSekar
 */
public class MarsRover{
    int row;
    int column;
    int xCoordinate;
    int yCoordinate;
    String initialPosition;
    String instructions;
MarsRover(int row,int column,int xCoordinate,int yCoordinate,String initialPosition,String instructions){
this.row=row;
this.column=column;
this.xCoordinate=xCoordinate;
this.yCoordinate=yCoordinate;
this.initialPosition = initialPosition;
this.instructions=instructions;

}
public String rightInstructions(String initialPosition){
    if (initialPosition=="North"){
    initialPosition="East";
    }
    else if(initialPosition=="East"){
        initialPosition="South";
}
    else if(initialPosition=="South"){
    initialPosition="West";
    }
    else{
    initialPosition="North";}
    return initialPosition;
}

public String leftInstructions(String initialPosition){
    if (initialPosition=="North"){
    initialPosition="West";
    }
    else if(initialPosition=="West"){
        initialPosition="South";
}
    else if(initialPosition=="South"){
    initialPosition="East";
    }
    else{
    initialPosition="North";}
    return initialPosition;
}
public int[] move(String initialPosition){
if (initialPosition=="East"){
this.xCoordinate+=1;
this.yCoordinate=this.yCoordinate;}
else if (initialPosition=="West"){
this.xCoordinate-=1;
this.yCoordinate=this.yCoordinate;}
else if (initialPosition=="North"){
this.xCoordinate=this.xCoordinate;
this.yCoordinate+=1;}
else if (initialPosition=="South"){
this.yCoordinate-=1;
this.xCoordinate=this.xCoordinate;}
int[] x=new int[2];
x[0]=this.xCoordinate;
x[1]=this.yCoordinate;
return x;


}
void givenInstruction(){
initialPosition=this.initialPosition;
int[] x={this.xCoordinate,this.yCoordinate};
for (int i=0;i<this.instructions.length();i++){
    char instruction=this.instructions.charAt(i);
    if (instruction=='R'){
        initialPosition=this.rightInstructions(initialPosition);
    }
    else if(instruction=='L'){
    initialPosition=this.leftInstructions(initialPosition);
    }
    else if(instruction=='M'){
x=this.move(initialPosition);
}
    
}
System.out.println(x[0]+" "+x[1]+" "+initialPosition);
}
public static void main(String args[]){
MarsRover m1=new MarsRover(5,5,1,2,"North","LMLMLMLMM");
m1.givenInstruction();
MarsRover m2=new MarsRover(5,5,3,3,"East","MMRMMRMRRM");
m2.givenInstruction();
}
}



