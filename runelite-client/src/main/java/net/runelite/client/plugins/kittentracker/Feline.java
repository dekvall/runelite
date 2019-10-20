/**
 * Copyright (c) 2018, Nachtmerrie <https://github.com/Nachtmerrie>
 * Copyright (c) 2019, dekvall <https://github.com/dekvall>
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
package net.runelite.client.plugins.kittentracker;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;
import java.util.Map;
import net.runelite.api.NpcID;

@Getter
@AllArgsConstructor
public enum Feline
{
	KITTEN_WHITE_BLACK(NpcID.KITTEN_5591, ItemID.PET_KITTEN, FelineType.KITTEN),
	CAT_WHITE_BLACK(NpcID.CAT_1619, ItemID.PET_CAT, FelineType.CAT),
	OVERGROWN_WHITE_BLACK(NpcID.OVERGROWN_CAT, ItemID.PET_CAT_1567, FelineType.OVERGROWN),
	LAZY_WHITE_BLACK(NpcID.LAZY_CAT, ItemID.LAZY_CAT, FelineType.LAZY),
	WILY_WHITE_BLACK(NpcID.WILY_CAT, ItemID.WILY_CAT, FelineType.WILY),

	KITTEN_WHITE(NpcID.KITTEN_5592, ItemID.PET_KITTEN_1556, FelineType.KITTEN),
	CAT_WHITE(NpcID.CAT_1620, ItemID.PET_CAT_1562, FelineType.CAT),
	OVERGROWN_WHITE(NpcID.OVERGROWN_CAT_5599, ItemID.PET_CAT_1568, FelineType.OVERGROWN),
	LAZY_WHITE(NpcID.LAZY_CAT_1627, ItemID.LAZY_CAT_6550, FelineType.LAZY),
	WILY_WHITE(NpcID.WILY_CAT_5585, ItemID.WILY_CAT_6556, FelineType.WILY),

	KITTEN_BROWN(NpcID.KITTEN_5593, ItemID.PET_KITTEN_1557, FelineType.KITTEN),
	CAT_BROWN(NpcID.CAT_1621, ItemID.PET_CAT_1563, FelineType.CAT),
	OVERGROWN_BROWN(NpcID.OVERGROWN_CAT_5600, ItemID.PET_CAT_1569, FelineType.OVERGROWN),
	LAZY_BROWN(NpcID.LAZY_CAT_1628, ItemID.LAZY_CAT_6551, FelineType.LAZY),
	WILY_BROWN(NpcID.WILY_CAT_5586, ItemID.WILY_CAT_6557, FelineType.WILY),

	KITTEN_BLACK(NpcID.KITTEN_5594, ItemID.PET_KITTEN_1558, FelineType.KITTEN),
	CAT_BLACK(NpcID.CAT_1622, ItemID.PET_CAT_1564, FelineType.CAT),
	OVERGROWN_BLACK(NpcID.OVERGROWN_CAT_5601, ItemID.PET_CAT_1570, FelineType.OVERGROWN),
	LAZY_BLACK(NpcID.LAZY_CAT_1629, ItemID.LAZY_CAT_6552, FelineType.LAZY),
	WILY_BLACK(NpcID.WILY_CAT_5587, ItemID.WILY_CAT_6558, FelineType.WILY),

	KITTEN_WHITE_BROWN(NpcID.KITTEN_5595, ItemID.PET_KITTEN_1559, FelineType.KITTEN),
	CAT_WHITE_BROWN(NpcID.CAT_1623, ItemID.PET_CAT_1565, FelineType.CAT),
	OVERGROWN_WHITE_BROWN(NpcID.OVERGROWN_CAT_5602, ItemID.PET_CAT_1571, FelineType.OVERGROWN),
	LAZY_WHITE_BROWN(NpcID.LAZY_CAT_1630, ItemID.LAZY_CAT_6553, FelineType.LAZY),
	WILY_WHITE_BROWN(NpcID.WILY_CAT_5588, ItemID.WILY_CAT_6559, FelineType.WILY),

	KITTEN_WHITE_BLUE(NpcID.KITTEN_5596, ItemID.PET_KITTEN_1560, FelineType.KITTEN),
	CAT_WHITE_BLUE(NpcID.CAT_1624, ItemID.PET_CAT_1566, FelineType.CAT),
	OVERGROWN_WHITE_BLUE(NpcID.OVERGROWN_CAT_5603, ItemID.PET_CAT_1572, FelineType.OVERGROWN),
	LAZY_WHITE_BLUE(NpcID.LAZY_CAT_1631, ItemID.LAZY_CAT_6554, FelineType.LAZY),
	WILY_WHITE_BLUE(NpcID.WILY_CAT_5589, ItemID.WILY_CAT_6560, FelineType.WILY),

	KITTEN_HELL(NpcID.HELLKITTEN, ItemID.HELLKITTEN, FelineType.KITTEN),
	CAT_HELL(NpcID.HELLCAT, ItemID.HELL_CAT, FelineType.CAT),
	OVERGROWN_HELL(NpcID.OVERGROWN_HELLCAT, ItemID.OVERGROWN_HELLCAT, FelineType.OVERGROWN),
	LAZY_HELL(NpcID.LAZY_HELLCAT, ItemID.LAZY_HELL_CAT FelineType.LAZY),
	WILY_HELL(NpcID.WILY_HELLCAT, ItemID.WILY_HELLCAT, FelineType.WILY);

	private final int id;
	private final int itemSpriteId;
	private FelineType type;

	private static final Map<Integer, Feline> felines;

	static
	{
		ImmutableMap.Builder<Integer, Feline> builder = new ImmutableMap.Builder<>();

		for (Feline feline : values())
		{
			builder.put(feline.id, feline);
		}

		felines = builder.build();
	}

	public static Feline of(int id)
	{
		return felines.get(id);
	}
}
