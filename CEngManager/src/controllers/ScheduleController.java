package controllers;

import repositories.ScheduleRepository;

public class ScheduleController{

	@SuppressWarnings("unused")
	private ScheduleRepository scheduleRepository;
	
	public ScheduleController() {
		this.scheduleRepository = new ScheduleRepository();
	}
}
