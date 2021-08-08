package com.github.gisellevonbingen.moremekanismprocessing.common.material;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public enum MaterialResultShape
{
	INGOT(MaterialState.ORE, MaterialState.CRYSTAL, MaterialState.SHARD, MaterialState.CLUMP, MaterialState.DIRTY_DUST, MaterialState.DUST, MaterialState.INGOT, MaterialState.NUGGET),
	GEM(MaterialState.ORE, MaterialState.CRYSTAL, MaterialState.SHARD, MaterialState.CLUMP, MaterialState.DIRTY_DUST, MaterialState.DUST, MaterialState.GEM),
	// EOL
	;

	private List<MaterialState> processableStates;

	private MaterialResultShape(MaterialState... processableStates)
	{
		this(Lists.newArrayList(processableStates));
	}

	private MaterialResultShape(List<MaterialState> processableStates)
	{
		this.processableStates = Lists.newArrayList(processableStates);
		this.ensureStatesValid();
	}

	public boolean isExclusiveStates(List<MaterialState> states, Iterable<MaterialState> exclusiveStates)
	{
		boolean contains = false;

		for (MaterialState state : exclusiveStates)
		{
			if (this.canProcess(state) == true)
			{
				if (contains == false)
				{
					contains = true;
				}
				else
				{
					return false;
				}

			}

		}

		return true;
	}

	public void ensureStatesValid()
	{
		List<MaterialState> exclusiveStates = Arrays.asList(MaterialState.INGOT, MaterialState.GEM);

		if (this.isExclusiveStates(this.processableStates, exclusiveStates) == false)
		{
			throw new IllegalArgumentException(this.name() + " require exclusiveStates :" + exclusiveStates);
		}

	}

	public boolean canProcess(MaterialState state)
	{
		return this.processableStates.contains(state);
	}

	public boolean canProcess(Iterable<MaterialState> states)
	{
		for (MaterialState state : states)
		{
			if (this.processableStates.contains(state) == false)
			{
				return false;
			}

		}

		return true;
	}

	public List<MaterialState> getProcessableStates()
	{
		return Lists.newArrayList(this.processableStates);
	}

}
