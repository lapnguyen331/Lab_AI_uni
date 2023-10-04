package ex3;
//NOTE : agent :# , map *, dirt: $

import java.util.*;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public int dirtAmount ; //số lượng dirt trong map
	public char[][] gameMap;

//	public static final String LOCATION_A = "A";
//	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null; //NOTE:số lượng agen
	private Map<AgentLocation,Environment.LocationState> cleanLocationList = new HashMap<AgentLocation, LocationState>(); 

	public Environment(int n, int m, double dirpercent) {
		envState = new EnvironmentState(createMap(n,m,dirpercent));
	}
//	public Environment(char[][] map) {
//		envState = new EnvironmentState(map);
//	}
	//thêm rác
	public void addRub(){

	}
	// add an agent into the enviroment

	public Agent getAgent() {
		return agent;
	}

	public void addAgent(Agent agent, int locationX, int locationY) {
		this.agent= agent;
		this.envState.setAgentLocation(locationX,locationY); //Note: chưa set trạng thái ban đẩu cho location

		// TODO
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	int xcurrent;
	int ycurrent;
	public EnvironmentState executeAction(Action action,AgentLocation currentagentLocation) {
		if(action.isNoOp()) return envState;
//		AgentLocation currentagentLocation = envState.getAgentLocation(); //vị trí agent đang đứng
		this.xcurrent = currentagentLocation.getX();
		this.ycurrent = currentagentLocation.getY();
		if(!action.isNoOp()) {
			int colMap = gameMap[0].length;
			int rolMap = gameMap.length;
			if (action == Environment.SUCK_DIRT) { //vị trí agent đang đứng bẩn -->chắc chắn phải dọn
				envState.setLocationState(currentagentLocation, LocationState.CLEAN);
				return envState;
			} else if (xcurrent == 0) {
				if(ycurrent == 0){ //lề trên
					if (action == Environment.MOVE_RIGHT){
						moving(false,true,false,false);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;

					}
					if(action == Environment.MOVE_DOWN){
						moving(false,false,true,false);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;
					}
				}
				if(ycurrent == colMap -1){
					if(action == Environment.MOVE_LEFT){
						moving(false,true,false,false);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;
					}
					if(action == Environment.MOVE_DOWN){
						moving(false,false,true,false);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;
					}
				}
				if(0 <xcurrent && xcurrent < colMap-1){
					moving(false,false,true,false);
					envState.setAgentLocation(xcurrent,ycurrent);
					return envState;
				}
			} else if (ycurrent == 0) {
				if (ycurrent == rolMap - 1) { //lề trái
					if (action == MOVE_RIGHT) {
						moving(false, true, false, false);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
					if (action == MOVE_UP) {
						moving(false, false, true, false);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
				}
				if (0 < ycurrent && ycurrent < rolMap - 1) {
					if (action == MOVE_RIGHT) {
						moving(false, true, false, false);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
					if (action == MOVE_UP) {
						moving(false, false, true, false);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
					if (action == MOVE_DOWN) {
						moving(false, false, false, true);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
				}
				if(xcurrent >0 && xcurrent < rolMap -1){
					if(action == MOVE_DOWN){
						moving(false,false,false,true);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
					if(action == MOVE_LEFT){
						moving(true,false,false,false);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
					if(action == MOVE_RIGHT){
						moving(false,true,false,false);
						envState.setAgentLocation(xcurrent, ycurrent);
						return envState;
					}
				}
			} else if (xcurrent == rolMap -1) {//lề phải
				if(ycurrent == colMap -1){
					if(action == MOVE_LEFT){
						moving(true,false,false,false);
						envState.setAgentLocation(xcurrent,ycurrent);return envState;
					}
					if(action == MOVE_UP){
						moving(false,false,true,false);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;
					}
				}
				if(0<ycurrent && ycurrent < colMap -1){
					if(action == MOVE_LEFT){
						moving(true,false,false,false);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;
					}
					if(action == MOVE_DOWN){
						moving(false,false,false,true);
						envState.setAgentLocation(xcurrent,ycurrent);
						return envState;
					}
				}
			} else if (0 <xcurrent && 0<ycurrent&&  xcurrent<rolMap-1 &&ycurrent <colMap-1) {
				if(action == MOVE_LEFT){
					moving(true,false,false,false);
					envState.setAgentLocation(xcurrent,ycurrent);
					return envState;
				}
				if (action == MOVE_UP){
					moving(false,false,true,false);
					envState.setAgentLocation(xcurrent,ycurrent);
					return envState;
				}
				if(action == MOVE_DOWN){
					moving(false,false,false,true);
					envState.setAgentLocation(xcurrent,ycurrent);
					return envState;
				}
				if(action == MOVE_RIGHT){
					moving(false,true,false,false);
					envState.setAgentLocation(xcurrent,ycurrent);
					return envState;
				}
			}
		}

//		System.out.println("/"+this.xcurrent+","+this.ycurrent);
		// TODO
		return envState;
	}
	public void moving(boolean left, boolean right, boolean up, boolean down){
		if(left) {
			this.xcurrent -=1;
		} else if(right) {
			this.xcurrent += 1;
		} else if(up) {
			this.ycurrent +=1;
		} else if(down) {
			this.ycurrent -=1;
		}
	}
//	public boolean checkTopEdge(int x, int y){
//		if(x == 0 ){
//			return true;
//		}
//		return false;
//	}
//	public boolean checkRightEdge(int x, int y){
//		if(y ==)
//	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		Percept currentpc = new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation())) ;
		return currentpc;
		// TODO
	}
	//tạo map và random dirt
	public char[][] createMap(int n, int m, double dirtPercent){
		char[][] map = new char[n][m];
		for (int i = 0; i <n ; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = '*';
			}
		}
		//random dirt
		Random xL = new Random();
		Random yL = new Random();
		if(dirtPercent >= 1){
			dirtPercent =0.5;
		}
		dirtAmount = (int) (dirtPercent * n *m);
		List<Integer> chosenPositions = new ArrayList<>();//danh sách vị trí random
		Random random = new Random();

		// Sinh ngẫu nhiên vị trí không trùng lặp trong ma trận
		for (int i = 0; i < dirtAmount; i++) {
			int randomPosition;
			do {
				randomPosition = random.nextInt(n * m);
			} while (chosenPositions.contains(randomPosition));

			chosenPositions.add(randomPosition);
			int row = randomPosition / m;
			int col = randomPosition % m;
			map[row][col] = '#';
		}
		this.gameMap = map;
		return gameMap;
	}
	public void step() {
		envState.display();
		AgentLocation agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction,agentLocation);
		System.out.println();
		System.out.println("Agent Loc:[" + agentLocation.getX()+","+agentLocation.getY()+"]" + "\tAction: " + anAction);
		//TODO:load map check dk hoàn thành
		if(envState.getLocationState(agentLocation) == Environment.LocationState.CLEAN){
			for (Map.Entry<AgentLocation,Environment.LocationState> element : this.cleanLocationList.entrySet()){ //duyệt ds location clean
				if(!(element.getKey().equals(agentLocation))){ //kiểm tra xem vị trí đứng đã có trong ds chưa?
					this.cleanLocationList.put(agentLocation,envState.getLocationState(agentLocation));
				}
			}
		}
		if(this.cleanLocationList.size() == envState.getMapGame().size()){
			isDone =true; // if both location are clean, then agent do not need to do any action
		}
		es.display();
	}
	
	public void step(int n) {
		System.out.println("Map đã được khởi tạo :" +gameMap.length +"x"+gameMap[0].length+" ; "+ "số lượng ô rác: " + dirtAmount+"------");
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("\n-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}

//	public static void main(String[] args) {
//		Environment em = new Environment(2,3,0.5);
//		System.out.println(em.executeAction(Environment.MOVE_LEFT,new AgentLocation(0,0)).getAgentLocation().getY());
//
//	}
}
