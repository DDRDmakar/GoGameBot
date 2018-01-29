package backend.field;

public class GoNode {
	
	private int value = 0;
	private int ID;
	private GoNode left, right, top, bottom;
	
	public GoNode getLeft()   { return left; }
	public GoNode getRight()  { return right; }
	public GoNode getTop()    { return top; }
	public GoNode getBottom() { return bottom; }
	
	public int getValue() { return value; }
	public int getID() { return ID; }
	
	public void setLeft(GoNode val)   { this.left = val; }
	public void setRight(GoNode val)  { this.right = val; }
	public void setTop(GoNode val)    { this.top = val; }
	public void setBottom(GoNode val) { this.bottom = val; }
	
	public void setValue(int value) { this.value = value; }
	public void setID(int ID) { this.ID = ID; }
	
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
