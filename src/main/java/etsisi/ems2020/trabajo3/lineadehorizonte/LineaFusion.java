package etsisi.ems2020.trabajo3.lineadehorizonte;

public class LineaFusion {

	private LineaHorizonte s1;
	private LineaHorizonte s2;
	

	public LineaFusion( LineaHorizonte s1,LineaHorizonte s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public LineaHorizonte getS1() {
		return s1;
	}
	public void setS1(LineaHorizonte s1) {
		this.s1 = s1;
	}
	public LineaHorizonte getS2() {
		return s2;
	}
	public void setS2(LineaHorizonte s2) {
		this.s2 = s2;
	}
}
