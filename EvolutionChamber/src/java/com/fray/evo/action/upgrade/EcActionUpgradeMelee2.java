package com.fray.evo.action.upgrade;

import java.util.ArrayList;
import java.util.List;

import com.fray.evo.EcBuildOrder;
import com.fray.evo.EcEvolver;
import com.fray.evo.EcState;
import com.fray.evo.action.EcAction;
import com.fray.evo.action.build.EcActionBuildEvolutionChamber;
import com.fray.evo.action.build.EcActionBuildLair;
import com.fray.evo.util.UpgradeLibrary;

public class EcActionUpgradeMelee2 extends EcActionUpgrade
{
	@Override
	public void init()
	{
		init(UpgradeLibrary.Melee2);
	}

	@Override
	public boolean isInvalid(EcBuildOrder s)
	{
		if (s.getEvolutionChambers() == 0)
			return true;
		if (s.getLairs() == 0 && s.getHives() == 0 && s.evolvingLairs == 0 && s.evolvingHives == 0)
			return true;
		if (s.isMelee1() == false)
			return true;
		if (s.isMelee2() == true)
			return true;
		return false;
	}

	@Override
	public void execute(EcBuildOrder s, EcEvolver e)
	{
		super.execute(s, e);
		s.evolutionChambersInUse++;
	}

	@Override
	public boolean isPossible(EcBuildOrder s)
	{
		if (s.evolutionChambersInUse == s.getEvolutionChambers())
			return false;
		return super.isPossible(s);
	}

	@Override
	public void afterTime(EcBuildOrder s, EcEvolver e)
	{
		superAfterTime(s, e);
		s.evolutionChambersInUse--;
	}

	@Override
	public List<EcAction> requirements(EcState destination)
	{
		ArrayList<EcAction> l = new ArrayList<EcAction>();
		l.add(new EcActionBuildEvolutionChamber());
		l.add(new EcActionBuildLair());
		l.add(new EcActionUpgradeMelee1());
		return l;
	}
}