package ex3;


import javax.xml.stream.Location;

public class Percept {
	private AgentLocation agentLocation;
	private Environment.LocationState state;

	public Percept(AgentLocation agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public AgentLocation getAgentLocation() {
		return this.agentLocation;
	}
}