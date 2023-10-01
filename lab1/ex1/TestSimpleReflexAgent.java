package ex1;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.DIRTY, Environment.LocationState.CLEAN);
		Agent agent1 = new Agent(new AgentProgram());
		env.addAgent(agent1,Environment.LOCATION_A);
		env.step(3);
	}
}
