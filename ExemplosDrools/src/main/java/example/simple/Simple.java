package example.simple;

public class Simple {
	
	private int input;
	private boolean output = false;

	
	public Simple(int input) {
		this.input = input;
	}

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}

	public boolean isOutput() {
		return output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}
}
