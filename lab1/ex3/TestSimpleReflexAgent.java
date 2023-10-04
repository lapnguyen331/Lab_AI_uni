package ex3;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {

		Environment env = new Environment(2,3,0.5);
//		AgentLocation m1 = new AgentLocation(0,0);
		Agent agent1 = new Agent(new AgentProgram());
		env.addAgent(agent1, 0,0);
		env.step(20);
	}
}
