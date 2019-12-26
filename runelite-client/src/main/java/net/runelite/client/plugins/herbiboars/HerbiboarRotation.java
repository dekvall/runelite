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
package net.runelite.client.plugins.herbiboars;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.coords.WorldPoint;

@Getter
@Slf4j
public enum HerbiboarRotation
{
	// Middle
	MIDDLE_NORTH_1(HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM),
	MIDDLE_NORTH_2(HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	MIDDLE_NORTH_3(HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.B_SEAWEED),
	MIDDLE_NORTH_4(HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.C_MUSHROOM),
	MIDDLE_NORTH_5(HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.H_SEAWEED_EAST),

	MIDDLE_EAST_1(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_PATCH),
	MIDDLE_EAST_2(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM),
	MIDDLE_EAST_3(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.H_SEAWEED_EAST),
	MIDDLE_EAST_4(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	MIDDLE_EAST_5(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	MIDDLE_EAST_6(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.G_MUSHROOM),
	MIDDLE_EAST_7(HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),

	MIDDLE_SOUTH_1(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.G_MUSHROOM),
	MIDDLE_SOUTH_2(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	MIDDLE_SOUTH_3(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED),
	MIDDLE_SOUTH_4(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM),
	MIDDLE_SOUTH_5(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_MUSHROOM),
	MIDDLE_SOUTH_6(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.C_MUSHROOM),
	MIDDLE_SOUTH_7(HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM),


	LEPRECHAUN_NORTH_1(HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_PATCH, HerbiboarSearchSpot.A_PATCH),
	LEPRECHAUN_NORTH_2(HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	LEPRECHAUN_NORTH_3(HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED),
	LEPRECHAUN_NORTH_4(HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.B_SEAWEED),
	LEPRECHAUN_NORTH_5(HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.H_SEAWEED_EAST),
	LEPRECHAUN_NORTH_6(HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.C_PATCH, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM),
	LEPRECHAUN_NORTH_7(HerbiboarSearchSpot.G_MUSHROOM, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	LEPRECHAUN_NORTH_8(HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	LEPRECHAUN_NORTH_9(HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_PATCH, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.C_MUSHROOM),
	LEPRECHAUN_NORTH_10(HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_MUSHROOM),

	CAMP_WEST_1(HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_MUSHROOM),
	CAMP_WEST_2(HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.F_PATCH),

	CAMP_EAST_1(HerbiboarSearchSpot.J_PATCH, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.C_MUSHROOM),
	CAMP_EAST_2(HerbiboarSearchSpot.J_PATCH, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM),
	CAMP_EAST_3(HerbiboarSearchSpot.J_PATCH, HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.G_MUSHROOM),
	CAMP_EAST_4(HerbiboarSearchSpot.J_PATCH, HerbiboarSearchSpot.H_SEAWEED_EAST),

	// Ghost mushroom is complete i think
	GHOST_WEST_1(HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.F_PATCH),
	GHOST_WEST_2(HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_PATCH, HerbiboarSearchSpot.A_PATCH),
	GHOST_WEST_3(HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_PATCH, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.B_SEAWEED),
	GHOST_WEST_4(HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_MUSHROOM),

	GHOST_NORTH_1(HerbiboarSearchSpot.K_PATCH, HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_MUSHROOM),
	GHOST_NORTH_2(HerbiboarSearchSpot.K_PATCH, HerbiboarSearchSpot.J_PATCH, HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),


	DRIFTWOOD_1(HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.I_PATCH, HerbiboarSearchSpot.E_PATCH, HerbiboarSearchSpot.A_PATCH),
	DRIFTWOOD_2(HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.D_PATCH, HerbiboarSearchSpot.G_MUSHROOM),
	DRIFTWOOD_3(HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_PATCH, HerbiboarSearchSpot.A_PATCH),
	DRIFTWOOD_4(HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.D_SEAWEED, HerbiboarSearchSpot.B_SEAWEED, HerbiboarSearchSpot.A_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	DRIFTWOOD_5(HerbiboarSearchSpot.H_SEAWEED_WEST, HerbiboarSearchSpot.G_PATCH, HerbiboarSearchSpot.F_MUSHROOM, HerbiboarSearchSpot.E_MUSHROOM, HerbiboarSearchSpot.I_MUSHROOM),
	;

	private final List<HerbiboarSearchSpot> rotation;

	HerbiboarRotation(HerbiboarSearchSpot... spots)
	{
		this.rotation = ImmutableList.copyOf(spots);
	}

	static List<HerbiboarRotation> findMatchingRotations(List<HerbiboarSearchSpot> currentPath, HerbiboarSearchSpot.Group groupToSearch)
	{
		List<HerbiboarRotation> matchingRotations = Lists.newArrayList(values());
		for (HerbiboarRotation rotation : values())
		{
			List<HerbiboarSearchSpot> spotOrder = rotation.getRotation();
			int startIndex = Collections.indexOfSubList(spotOrder, currentPath);
			if (startIndex != 0
				|| spotOrder.size() <= currentPath.size()
				|| spotOrder.get(currentPath.size()).getGroup() != groupToSearch)
			{
				matchingRotations.remove(rotation);
			}
		}
		return matchingRotations;
	}

	static WorldPoint extractLocation(List<HerbiboarRotation> rotations, int pathIndex)
	{
		Set<HerbiboarSearchSpot> relevant = rotations.stream()
			.map(r -> r.getRotation().get(pathIndex))
			.collect(Collectors.toSet());

		if (relevant.size() == 1)
		{
			return relevant.stream().findFirst().get().getLocation();
		}
		else
		{
			return null;
		}
	}
}
