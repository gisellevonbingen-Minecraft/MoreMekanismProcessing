package com.github.gisellevonbingen.moremekanismprocessing.common.data;

import java.util.HashSet;

import net.minecraftforge.common.data.ExistingFileHelper;

public class EmptyExistingFileHelper extends ExistingFileHelper
{
	public EmptyExistingFileHelper()
	{
		super(new HashSet<>(), new HashSet<>(), false, null, null);
	}

}
