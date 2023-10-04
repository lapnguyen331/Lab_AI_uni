package ex3;



import java.util.HashMap;
import java.util.Map;
// NOTE : đang lưu trữ vị trí, trạng thái vị trí agent
public class EnvironmentState {
	private Map<AgentLocation, Environment.LocationState> state = new HashMap<AgentLocation, Environment.LocationState>();
	private AgentLocation agentLocation = new AgentLocation(-1,-1) ;


	public EnvironmentState(char[][] map) {
//		this.state.put(Environment.LOCATION_A, locAState);
//		this.state.put(Environment.LOCATION_B, locBState);
		for (int i = 0; i < map.length; i++) { //load map của game vào MAP
			for (int j = 0; j < map[0].length; j++) {
				AgentLocation em = new AgentLocation(i,j);
				Environment.LocationState statetemp =null;
				if(map[i][j] == '#'){
					statetemp = Environment.LocationState.DIRTY;
				}
				if(map[i][j] =='*'){
					statetemp = Environment.LocationState.CLEAN;
				}
				this.state.put(em,statetemp);
			}
		}
	}
	public Map<AgentLocation,Environment.LocationState> getMapGame(){
		return this.state;
	}
	public void setAgentLocation( int locationX,int locationY) {
		this.agentLocation.setX(locationX);
		this.agentLocation.setY(locationY);
	}

	public AgentLocation getAgentLocation() {
		return this.agentLocation;
	}

	public Environment.LocationState getLocationState(AgentLocation location) {
		for (Map.Entry<AgentLocation,Environment.LocationState> element: this.state.entrySet()) {
			if(element.getKey().getX() == location.getX() && element.getKey().getY() == location.getY()){
				return element.getValue();
			}
		}
		return null;
	}

	public void setLocationState(AgentLocation location, Environment.LocationState locationState) {
		for (Map.Entry<AgentLocation,Environment.LocationState> element: this.state.entrySet()) {
			if(element.getKey().getX() == location.getX() && element.getKey().getY() == location.getY()){
				element.setValue(locationState);
			}
		}
	}

	public void display() {
		System.out.println("Environment state: ");
		for (Map.Entry<AgentLocation,Environment.LocationState> element:this.state.entrySet()) {
			System.out.print("{"+"["+element.getKey().getX()+"," + element.getKey().getY()+"]" +","+element.getValue()+"}" +"/"+this.agentLocation.getX()+","+this.getAgentLocation().getY()+"/" +this.getLocationState(this.agentLocation));
		}

	}

}