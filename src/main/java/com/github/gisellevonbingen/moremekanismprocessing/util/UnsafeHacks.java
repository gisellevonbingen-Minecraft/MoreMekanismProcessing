package com.github.gisellevonbingen.moremekanismprocessing.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeHacks
{
	public static final Unsafe UNSAFE;

	static
	{
		try
		{
			final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			UNSAFE = (Unsafe) theUnsafe.get(null);
		}
		catch (IllegalAccessException | NoSuchFieldException e)
		{
			throw new RuntimeException("BARF!", e);
		}

	}

}
