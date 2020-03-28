/*
 * Copyright (c) 2020, dekvall <https://github.com/dekvall>
 * Copyright (c) 2020, Jordan <nightfirecat@protonmail.com>
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
package net.runelite.client.plugins.tracking;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum TrackingRule
{
	A_SOUTH(TrackingSearchSpot.Group.A, TrackingStart.MIDDLE),
	C_WEST(TrackingSearchSpot.Group.C, TrackingStart.MIDDLE),
	D_WEST_1(TrackingSearchSpot.Group.D, TrackingStart.MIDDLE),
	D_WEST_2(TrackingSearchSpot.Group.D, TrackingSearchSpot.Group.C),
	E_NORTH(TrackingSearchSpot.Group.E, TrackingSearchSpot.Group.A),
	F_EAST(TrackingSearchSpot.Group.F, TrackingSearchSpot.Group.G),
	G_NORTH(TrackingSearchSpot.Group.G, TrackingSearchSpot.Group.F),
	H_NORTH(TrackingSearchSpot.Group.H, TrackingSearchSpot.Group.D),
	H_EAST(TrackingSearchSpot.Group.H, TrackingStart.DRIFTWOOD),
	I_EAST(TrackingSearchSpot.Group.I, TrackingStart.LEPRECHAUN),
	I_SOUTH_1(TrackingSearchSpot.Group.I, TrackingStart.GHOST_MUSHROOM),
	I_SOUTH_2(TrackingSearchSpot.Group.I, TrackingStart.CAMP_ENTRANCE),
	I_WEST(TrackingSearchSpot.Group.I, TrackingSearchSpot.Group.E),
	;

	private final TrackingSearchSpot.Group to;
	private final TrackingStart fromStart;
	private final TrackingSearchSpot.Group fromGroup;

	TrackingRule(TrackingSearchSpot.Group to, TrackingSearchSpot.Group from)
	{
		this(to, null, from);
	}

	TrackingRule(TrackingSearchSpot.Group to, TrackingStart fromStart)
	{
		this(to, fromStart, null);
	}

	/**
	 * Returns whether the next {@link TrackingSearchSpot} can be deterministically selected based on the starting
	 * location and the path taken so far, based on the rules defined on the OSRS wiki.
	 *
	 * {@see https://oldschool.runescape.wiki/w/Herbiboar#Guaranteed_tracks}
	 *
	 * @param start       Herbiboar's starting spot where the tracking path begins
	 * @param currentPath A list of {@link TrackingSearchSpot}s which have been searched thus far, and the next one to search
	 * @return {@code true} if a rule can be applied, {@code false} otherwise
	 */
	static boolean canApplyRule(TrackingStart start, List<TrackingSearchSpot> currentPath)
	{
		if (start == null || currentPath.isEmpty())
		{
			return false;
		}

		int lastIndex = currentPath.size() - 1;
		TrackingSearchSpot.Group goingTo = currentPath.get(lastIndex).getGroup();

		for (TrackingRule rule : values())
		{
			if (lastIndex > 0 && rule.matches(currentPath.get(lastIndex - 1).getGroup(), goingTo)
			|| lastIndex == 0 && rule.matches(start, goingTo))
			{
				return true;
			}
		}

		return false;
	}

	boolean matches(TrackingStart from, TrackingSearchSpot.Group to)
	{
		return this.matches(from, null, to);
	}

	boolean matches(TrackingSearchSpot.Group from, TrackingSearchSpot.Group to)
	{
		return this.matches(null, from, to);
	}

	boolean matches(TrackingStart fromStart, TrackingSearchSpot.Group fromGroup, TrackingSearchSpot.Group to)
	{
		return this.to == to
			&& (fromStart != null && this.fromStart == fromStart || fromGroup != null && this.fromGroup == fromGroup);
	}
}
