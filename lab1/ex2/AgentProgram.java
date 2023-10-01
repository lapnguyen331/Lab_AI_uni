package ex2;

import java.util.Random;

public class AgentProgram {
	// NOTE- map [a,b]  stop:0 (if can not moving )left =1, right =2, up=3 , down =4
	//NOTE       [c,d]
	public Action execute(Percept p) {// location, status

		if(p.getLocationState() == Environment.LocationState.DIRTY){
			return Environment.SUCK_DIRT;
		}
		else {
			int moving = randomActionMoving();
			if(moving == 1){
				return Environment.MOVE_LEFT;
			} else if (moving == 2) {
				return Environment.MOVE_RIGHT;
			} else if (moving == 3) {
				return Environment.MOVE_UP;
			} else if (moving ==4) {
				return Environment.MOVE_DOWN;
			}
		}
		//TODo
		return NoOpAction.NO_OP;
	}
	public int randomActionMoving(){
		Random rd= new Random();

		int ran = rd.nextInt(1,4);
		return ran;
	}

//	public static void main(String[] args) {
//		AgentProgram a = new AgentProgram();
//		for (int i = 0; i < 200; i++) {
//			System.out.println(a.rendomActionMoving());
//
//		}
//	}
}