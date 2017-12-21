package backend.field;

public class GoNode {
	
	private int value;
	private GoNode left, right, top, bottom;
	
	public GoNode() {
		this(null, null, null, null);
	}
	
	public GoNode(GoNode left, GoNode right, GoNode top, GoNode Bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}
}
