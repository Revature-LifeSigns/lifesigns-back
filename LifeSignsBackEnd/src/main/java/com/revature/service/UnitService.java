package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Unit;
import com.revature.model.User;
import com.revature.repository.UnitRepository;

@Service("unitService")
@Transactional
public class UnitService {
	private UnitRepository uRepo;
	
	public UnitService() {
		
	}
	
	@Autowired
	public UnitService(UnitRepository uRepo) {
		super();
		this.uRepo = uRepo;
	}
	
	
	public List<Unit> getAllUnits(){
		List<Unit> units = uRepo.findAll();
		if(units.size() == 0) return null;
		return units;
	}
	
	public Unit getUnitByName(String unitName) {
		Unit unit = uRepo.findByUnit(unitName);
		if(unit == null) return null;
		return unit;
	}
	
    public void insertUnit(Unit unit) {
    	uRepo.save(unit);
    }
}
