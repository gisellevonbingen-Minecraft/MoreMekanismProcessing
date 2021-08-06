package com.github.gisellevonbingen.moremekanismprocessing.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeHelper
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

	public static void putBoolean(Object obejct, Field field, boolean value)
	{
		if (field == null)
		{
			return;
		}

		field.setAccessible(true);
		long offset = UNSAFE.objectFieldOffset(field);
		UNSAFE.putBoolean(obejct, offset, value);
	}

	public static boolean getBoolean(Object obejct, Field field)
	{
		field.setAccessible(true);
		long offset = UNSAFE.objectFieldOffset(field);
		return UNSAFE.getBoolean(obejct, offset);
	}

}
