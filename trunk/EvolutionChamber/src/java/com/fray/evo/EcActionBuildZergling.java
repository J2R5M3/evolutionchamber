package com.fray.evo;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class EcActionBuildZergling extends EcAction implements Serializable
{
	public void execute(final EcBuildOrder s,final EcEvolver e)
	{
		s.minerals -=50;
		s.consumeLarva(e);
		s.supplyUsed += 1;
		s.addFutureAction(24,new Runnable(){
			@Override
			public void run()
			{
				if (e.debug) System.out.println("@"+s.seconds()+" Zergling+2");
				s.zerglings +=2;
			}});
	}

	public boolean isInvalid(EcBuildOrder s)
	{
		if (s.spawningPools == 0)
			return true;
		return false;
	}
	
	public boolean isPossible(EcBuildOrder s)
	{
		if (s.minerals < 50)
			return false;
		if (s.larva < 1)
			return false;
		if (!s.hasSupply(1))
			return false;
		return true;
	}

	@Override
	public List<EcAction> requirements()
	{
		ArrayList<EcAction> l = new ArrayList<EcAction>();
		l.add(new EcActionBuildSpawningPool());
		return l;
	}
}
