package gisellevonbingen.mmp.common.data;

import java.util.HashSet;

import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EmptyExistingFileHelper extends ExistingFileHelper
{
	public EmptyExistingFileHelper()
	{
		super(new HashSet<>(), new HashSet<>(), false, null, null);
	}

}
