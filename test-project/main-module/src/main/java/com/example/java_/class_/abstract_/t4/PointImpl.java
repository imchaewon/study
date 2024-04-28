package com.example.java_.class_.abstract_.t4;

public class PointImpl implements Point{
	private double x;
	private double y;

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setCartesian(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getR() {
		return 0;
	}

	@Override
	public double getTheta() {
		return 0;
	}

	@Override
	public void setPolar(double r, double theta) {

	}
}