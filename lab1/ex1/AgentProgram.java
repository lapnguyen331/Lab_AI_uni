package ex1;

public class AgentProgram {
	// NOTE- map [a,b]
	//NOTE       [c,d]
	public Action execute(Percept p) {// location, status

		if(p.getLocationState() == Environment.LocationState.DIRTY){
			return Environment.SUCK_DIRT;
		}
		else if (p.getAgentLocation() == Environment.LOCATION_A) {
				return Environment.MOVE_RIGHT;
			}
		else if (p.getAgentLocation() == Environment.LOCATION_B) {
				return Environment.MOVE_LEFT;
		}

		//TODo
		return NoOpAction.NO_OP;
		
	}
}