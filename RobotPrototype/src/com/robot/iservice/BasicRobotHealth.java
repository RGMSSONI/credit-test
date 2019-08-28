package com.robot.iservice;

import com.robot.model.Robot;

public abstract class BasicRobotHealth {
	
	public abstract boolean canCarry(int weight);
	public abstract boolean powerCheck(Robot r, int charging);

}
