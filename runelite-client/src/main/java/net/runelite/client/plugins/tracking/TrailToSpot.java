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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Value;
import net.runelite.api.Varbits;

/**
 * A representation of a trail of footsteps which appears when hunting for the Herbiboar.
 */
@Value
class TrailToSpot
{
	/**
	 * The Varbit associated with the trail. When inactive, this Varbit's value should be less than
	 * {@link TrailToSpot#getValue()}. When this trail appears after searching a spot, this Varbit's value should be
	 * equal to that of {@link TrailToSpot#getValue()}. Once the next object along the trail has been searched, this
	 * Varbit's value will be greater than that of {@link TrailToSpot#getValue()}.
	 */
	private final Varbits varbit;
	/**
	 * The cutoff reference value to compare against the value of {@link TrailToSpot#getVarbit()} to determine its state
	 * along the current trail.
	 */
	private final int value;

	/**
	 * Some trails go around, and requires a conditional varbit value check.
	 */
	private final boolean requires4;
	/**
	 * The object ID of the footprints which appear when the trail is made visible.
	 */
	@Getter
	private final Set<Integer> footprintIds;

	TrailToSpot(Varbits varbit, int value, boolean requires4, int... footprintIds)
	{
		this.varbit = varbit;
		this.value = value;
		this.requires4 = requires4;
		this.footprintIds = ImmutableSet.copyOf(
			Arrays.stream(footprintIds)
				.boxed()
				.collect(Collectors.toSet())
		);
	}

	TrailToSpot(Varbits varbit, int value, int... footprintIds)
	{
		this(varbit, value, false, footprintIds);
	}

	TrailToSpot(Varbits varbit, int value, int footprintId)
	{
		this(varbit, value, footprintId, footprintId + 1);
	}
}
