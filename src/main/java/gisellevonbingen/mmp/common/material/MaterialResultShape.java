package gisellevonbingen.mmp.common.material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public enum MaterialResultShape
{
	DUST(null, Lists.newArrayList(MaterialState.ORE, MaterialState.CRYSTAL, MaterialState.SHARD, MaterialState.CLUMP, MaterialState.DIRTY_DUST, MaterialState.DUST)),
	INGOT(DUST, Lists.newArrayList(MaterialState.RAW, MaterialState.INGOT, MaterialState.NUGGET)),
	GEM(DUST, Lists.newArrayList(MaterialState.GEM)),
	// EOL
	;

	private MaterialResultShape parent;
	private List<MaterialState> processableStates;

	private MaterialResultShape(MaterialResultShape parent, List<MaterialState> processableStates)
	{
		this.parent = parent;
		this.processableStates = new ArrayList<>();

		if (parent != null)
		{
			this.processableStates.addAll(parent.getProcessableStates());
		}

		this.processableStates.addAll(processableStates);

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

	public MaterialResultShape getParent()
	{
		return this.parent;
	}

	public List<MaterialState> getProcessableStates()
	{
		return Lists.newArrayList(this.processableStates);
	}

}
