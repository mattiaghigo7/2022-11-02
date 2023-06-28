package it.polito.tdp.itunes.model;

public class Coppia {
	
	private Track t1;
	private Track t2;
	private int n;
	
	public Coppia(Track t1, Track t2, int n) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.n = n;
	}

	public Track getT1() {
		return t1;
	}

	public Track getT2() {
		return t2;
	}

	public int getN() {
		return n;
	}
	
}
