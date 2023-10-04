package ex3;

public class Agent {
	private AgentProgram program;

	public Agent() {
	}

	public Agent(AgentProgram aProgram) {
		program = aProgram;

	}

	public AgentProgram getProgram() {
		return program;
	}

	public void setProgram(AgentProgram program) {
		this.program = program;
	}



	public Action execute(Percept p) {
		if (program != null) {
			return program.execute(p);
		}
		return NoOpAction.NO_OP;
	}
}
