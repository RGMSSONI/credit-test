package com.robot.service;

import com.robot.iservice.BasicRobotHealth;
import com.robot.model.Robot;
import com.robot.model.Robot.Color;

public class RobotHealth extends BasicRobotHealth {
	
	@Override
	public boolean canCarry(int weight){
		if(weight > 10){
			System.out.println("Overweight");
			System.out.println("************************************************");
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean powerCheck(Robot r, int requiredCharging){
		int availbleCharging = r.getChargingStatus();
		if(availbleCharging < requiredCharging){
			System.out.println("Insufficient Charging For Task");
			System.out.println("************************************************");
			return false;
		}else{
			int remaining = availbleCharging - requiredCharging;
			r.setChargingStatus(remaining);
			if(remaining < 15){
				r.setHeadLightColor(Color.RED);
				System.out.println("*********************************************");
			}
			return true;
		}
		
		
	}

}
