/*
 * Copyright (c) 2019, Dekvall <https://github.com/dekvall>
 * Copyright (c) 2019, Daniel Teo <https://github.com/takuyakanbr>
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
package net.runelite.client.plugins.timetracking.cooking;

import com.google.common.collect.ImmutableMap;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.ui.ColorScheme;

@AllArgsConstructor
@Getter
enum BrewingState
{
	INGREDIENTS_ADDED(ColorScheme.PROGRESS_INPROGRESS_COLOR),
	YEAST_ADDED(ColorScheme.PROGRESS_COMPLETE_COLOR),
	SET(ColorScheme.PROGRESS_COMPLETE_COLOR),
	COMPLETE(ColorScheme.PROGRESS_COMPLETE_COLOR),
	//MATURE_COMPLETE(ColorScheme.PROGRESS_COMPLETE_COLOR),
	//BAD(ColorScheme.PROGRESS_ERROR_COLOR),
	EMPTY(ColorScheme.MEDIUM_GRAY_COLOR),
	WATER_ADDED(ColorScheme.MEDIUM_GRAY_COLOR),
	BARLEY_MALT_ADDED(ColorScheme.MEDIUM_GRAY_COLOR),
	UNKNOWN(ColorScheme.MEDIUM_GRAY_COLOR);

	private static final Map<Integer, BrewingState> BREW_STATES;

	static
	{
		ImmutableMap.Builder<Integer, BrewingState> builder = new ImmutableMap.Builder<>();
		BrewingState[] states = BrewingState.values();
		Integer[] brewValues;
		List<Integer> brewValueList;

		builder.put(0, EMPTY);
		builder.put(1, WATER_ADDED);
		builder.put(2, BARLEY_MALT_ADDED);

		for (Brew brew : Brew.values())
		{
			brewValueList = brew.getValues(); //TODO

			if (brewValueList == null) continue; //TODO

			brewValues = brewValueList.toArray(new Integer[0]);
			if (brew == Brew.CIDER)
			{
				//special case
				continue;
			}
			for (int i = 0; i < brewValues.length; i++)
			{
				builder.put(brewValues[i], states[i]);
			}
		}
		BREW_STATES = builder.build();
	}

	/**
	 * Gets the {@code BrewingState} corresponding to the given {@code VarPlayer} value.
	 */
	static BrewingState getState(int value)
	{
		return BREW_STATES.getOrDefault(value, UNKNOWN);
	}

	private final Color color;
}
