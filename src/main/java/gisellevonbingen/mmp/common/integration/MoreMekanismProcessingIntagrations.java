package gisellevonbingen.mmp.common.integration;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import gisellevonbingen.mmp.common.integration.biggerreactors.BiggerReactorsMod;
import gisellevonbingen.mmp.common.integration.extremereactors.ExtremeReactors2Mod;
import gisellevonbingen.mmp.common.integration.iceandfire.IceAndFireMod;
import gisellevonbingen.mmp.common.integration.libvulpes.LibVulpesMod;
import gisellevonbingen.mmp.common.integration.voluminousenergy.VoluminousEnergyMod;

public class MoreMekanismProcessingIntagrations
{
	private static final List<IntegrationMod> MODS = new ArrayList<>();
	public static final IceAndFireMod IceAndFire = new IceAndFireMod();
	public static final ExtremeReactors2Mod ExtremeReactors2 = new ExtremeReactors2Mod();
	public static final BiggerReactorsMod BiggerReactors = new BiggerReactorsMod();
	public static final LibVulpesMod LibVulpes = new LibVulpesMod();
	public static final VoluminousEnergyMod VoluminousEnergy = new VoluminousEnergyMod();

	public static void initialize()
	{
		IntegrationTags.initialize();

		MODS.add(IceAndFire);
		MODS.add(ExtremeReactors2);
		MODS.add(BiggerReactors);
		MODS.add(LibVulpes);
		MODS.add(VoluminousEnergy);
		MODS.forEach(m -> m.initialize());
	}

	public static List<IntegrationMod> getMods()
	{
		return Lists.newArrayList(MODS);
	}

}
