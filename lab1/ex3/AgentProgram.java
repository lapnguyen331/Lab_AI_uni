package ex3;

import java.util.Random;

public class AgentProgram {
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
	//random Action
	public int randomActionMoving(){
		Random rd= new Random();

		int ran = rd.nextInt(1,4);
		return ran;
	}
}