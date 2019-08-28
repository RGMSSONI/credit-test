package com.robot.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.robot.RobotMain;
import com.robot.iservice.RobotService;
import com.robot.model.Robot;
import com.robot.model.Robot.Color;
import com.robot.service.PrototypeRobot;
import com.robot.service.RobotHealth;

public class RobotTest {
	private RobotService robo;

	private RobotHealth rh;
	
	@Before
	public void setUp() throws Exception {		
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void robotMainTest(){
		RobotMain.main(String[] args);
		
		//main method is void, so does not return anything.
		assertEquals(null, null);
	}*/
	
	@Test
	public void roboTest() {
		robo = new PrototypeRobot();
		robo.walk(3.5);
		robo.walkAndCarry(1,13);
		robo.setCharging(60);
		assertEquals(true, robo.walk(4));
	}
	
	@Test
	public void roboTestHealthCheckFalse(){
		rh = new RobotHealth();
		
		boolean canCarry = rh.canCarry(12);
		assertEquals(false, canCarry);	
	}
	
	@Test
	public void roboTestHealthCheckTrue(){
		rh = new RobotHealth();
		
		boolean canCarry = rh.canCarry(8);
		assertEquals(true, canCarry);	
	}
	
	@Test
	public void roboTestHealthPowerCheckTrue(){
		rh = new RobotHealth();
		
		Robot r = new Robot();
		
		boolean canCarry = rh.powerCheck(r, 20);
		assertEquals(true, canCarry);	
	}
	
	@Test
	public void roboTestHealthPowerCheckFalse(){
		rh = new RobotHealth();
		
		Robot r = new Robot();
		r.setChargingStatus(30);
		
		boolean canCarry = rh.powerCheck(r, 50);
		assertEquals(false, canCarry);	
	}
	
	@Test
	public void roboTestHealthPowerCheckColor(){
		rh = new RobotHealth();
		
		Robot r = new Robot();
		r.setChargingStatus(40);
		
		rh.powerCheck(r, 30);
		
		Color c = r.getHeadLightColor();
		assertEquals(Color.RED, c);	
	}
	
	@Test
	public void roboTestHealthPowerCheckColorGreen(){
		rh = new RobotHealth();
		
		Robot r = new Robot();
		r.setChargingStatus(70);
		
		rh.powerCheck(r, 30);
		
		Color c = r.getHeadLightColor();
		assertEquals(Color.GREEN, c);	
	}
	
	@Test
	public void prototypeWalkTestFalse(){
		robo = new PrototypeRobot();
		
		boolean canWalk = robo.walk(15);
		assertEquals(false, canWalk);
	}
	
	@Test
	public void prototypeWalkTestTrue(){
		robo = new PrototypeRobot();
		
		boolean canWalk = robo.walk(3);
		assertEquals(true, canWalk);
	}
	
	@Test
	public void prototypeCarryTestTrue(){
		robo = new PrototypeRobot();
		
		boolean canCarry = robo.carry(5);
		assertEquals(true, canCarry);
	}
	
	@Test
	public void prototypeCarryTestFalse(){
		robo = new PrototypeRobot();
		
		boolean canCarry = robo.carry(15);
		assertEquals(false, canCarry);
	}
	
	@Test
	public void prototypeWalkCarryTestTrue(){
		robo = new PrototypeRobot();
		
		boolean canCarry = robo.walkAndCarry(3, 8);
		assertEquals(true, canCarry);
	}
	
	@Test
	public void prototypeWalkCarryTestFalse(){
		robo = new PrototypeRobot();
		
		boolean canCarry = robo.walkAndCarry(7, 9);
		assertEquals(false, canCarry);
	}
	
}
