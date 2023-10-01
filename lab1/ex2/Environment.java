package ex2;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public int point = 0;
	String scriptPoint= "";


	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent= agent;
		this.envState.setAgentLocation(location); //Note: chưa set trạng thái ban đẩu cho location

		// TODO
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		boolean isClean = false;
		boolean canMoving= false;


		if(action.isNoOp()) return envState;
		if(!action.isNoOp()){
			String currentAgentLo = envState.getAgentLocation(); //vị trí agent đang đứng
			if(action == Environment.SUCK_DIRT ){ //vị trí agent đang đứng bẩn -->chắc chắn phải dọn
				isClean = true;
				 envState.setLocationState(currentAgentLo, LocationState.CLEAN);
			}
			else {
				switch (currentAgentLo) {
					case Environment.LOCATION_A:
						if (action == MOVE_RIGHT ) {
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_B);
							break;
						} else if (action == MOVE_DOWN) {
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_C);
							break;
						}else {
							canMoving= false;
							break;
						}
					case Environment.LOCATION_B:
						if (action == MOVE_LEFT ) {
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_A);
							break;
						}
						else if (action == MOVE_DOWN){
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_D);
							break;
						}
						else {
							canMoving= false;
							break;
						}

					case Environment.LOCATION_C:
						if (action == MOVE_RIGHT ) {
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_D);
							break;
						} else if (action == MOVE_UP){
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_A);
							break;
						}
						else {
							canMoving= false;
							break;
						}
					case Environment.LOCATION_D:
						if (action == MOVE_UP ) {
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_B);
							break;
						} else if (action == MOVE_LEFT){
							canMoving = true;
							envState.setAgentLocation(Environment.LOCATION_C);
							break;
						}else {
							canMoving= false;
							break;
						}
				}
			}//count point
			if (isClean){
				this.point +=500;
				scriptPoint +="dọn dẹp +500/";

			}
			else {
				if(canMoving){
					this.point -=10;
					scriptPoint +="di chuyển -10/";
				}else {
					this.point -=100;
					scriptPoint +="không di chuyển được -100/";
				}
			}

		}
		// TODO
		return envState;
	}


	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		Percept currentpc = new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation())) ;
		return currentpc;
		// TODO
	}


	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction +"\tCurrent Point: "+point +"\tScrip: "+scriptPoint);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN)
		)
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
