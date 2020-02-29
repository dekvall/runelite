/*
 * Copyright (c) 2020, dekvall <https://github.com/dekvall>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.ammo;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.runelite.api.ItemID;

enum RetrievalDevice
{
	AVAS_ATTRACTOR(.6f, ItemID.AVAS_ATTRACTOR),
	AVAS_ACCUMULATOR(.72f,
		ItemID.AVAS_ACCUMULATOR, ItemID.ACCUMULATOR_MAX_CAPE,
		// Evidently you can use a vorkath's head on these (max and range capes)
		// To match the assemler bonusu of 80 %
		// This is most likely a varbit
		ItemID.RANGING_CAPE, ItemID.RANGING_CAPET, ItemID.RANGING_CAPE_10642,
		ItemID.MAX_CAPE, ItemID.MAX_CAPE_13342, ItemID.MAX_CAPE_13282),
	AVAS_ASSEMBLER(.8f, ItemID.AVAS_ASSEMBLER, ItemID.ASSEMBLER_MAX_CAPE),
	;

	private static final Map<Integer, Float> DEVICES;

	private final float retrievalChance;
	//private final Varbits varbit;
	private final int[] itemIds;

	static
	{
		ImmutableMap.Builder<Integer, Float> builder = new ImmutableMap.Builder<>();

		for (RetrievalDevice device : values())
		{
			for (int id : device.itemIds)
			{
				builder.put(id, device.retrievalChance);
			}
		}

		DEVICES = builder.build();
	}


	RetrievalDevice(float retrievalChance, int... itemIds)
	{
		this.retrievalChance = retrievalChance;
		this.itemIds = itemIds;
	}

	static float getRetrievalChance(int itemId)
	{
		return DEVICES.get(itemId);
	}
}
