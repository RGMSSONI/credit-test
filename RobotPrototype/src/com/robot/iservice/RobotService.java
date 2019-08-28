package com.robot.iservice;

public interface RobotService {

	boolean walk(double distance);
	boolean carry(int weight);
	boolean walkAndCarry(double distance, int weight);
	void setCharging(int percentage);
	void displayPrice(int barcode);
	
}
