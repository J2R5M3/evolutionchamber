package com.fray.evo;

import java.io.Serializable;

import com.fray.evo.fitness.EcEconFitness;
import com.fray.evo.fitness.EcFitness;
import com.fray.evo.fitness.EcFitnessType;
import com.fray.evo.fitness.EcStandardFitness;
import com.fray.evo.util.Race;

public class EcSettings implements Serializable
{
	public boolean workerParity = false;
	public boolean overDrone = false;
	public boolean useExtractorTrick = true;
	public boolean pullWorkersFromGas = true;
	public boolean pullThreeWorkersOnly = false;
	public EcFitnessType fitnessType = EcFitnessType.STANDARD;
	public int maximumExtractorTrickSupply = 200;
	public int minimumPoolSupply = 2;
	public int minimumExtractorSupply = 2;
	public int minimumHatcherySupply = 2;
	public Race race = Race.Zerg;


	
	private transient EcFitness ff;
	
	public EcFitness getFitnessFunction() {
		
		if(ff != null)
			return ff;
		
		switch(fitnessType) {
		case STANDARD:
			ff = new EcStandardFitness();
			break;
		case ECON:
			ff = new EcEconFitness();
			break;
		default:
			ff = new EcStandardFitness();
			break;
		}
		
		return ff;
		
	}
	
}