/*
 * Copyright (c) 2018 Abex
 * Copyright (c) 2018, Daniel Teo <https://github.com/takuyakanbr>
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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.client.Notifier;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.timetracking.SummaryState;
import net.runelite.client.plugins.timetracking.TimeTrackingConfig;

@Singleton
public class BrewingTracker
{
	static final int N_BREWING_STEPS = 2;

	private final Client client;
	private final ItemManager itemManager;
	private final TimeTrackingConfig config;
	private final Notifier notifier;

	@Getter(AccessLevel.PACKAGE)
	private final ConcurrentMap<BrewingPlace, Integer> brewingData = new ConcurrentHashMap<>();

	@Getter
	private SummaryState summary = SummaryState.UNKNOWN;

	@Getter
	private boolean anyComplete;

	private boolean allComplete;

	@Inject
	private BrewingTracker(Client client, ItemManager itemManager, TimeTrackingConfig config, Notifier notifier)
	{
		this.client = client;
		this.itemManager = itemManager;
		this.config = config;
		this.notifier = notifier;
	}

	public BrewingTabPanel createBrewingTabPanel()
	{
		return new BrewingTabPanel(itemManager, this, config);
	}

	public boolean updateData()
	{
		boolean changed = false;
		final Map<BrewingPlace, Integer> newData = new HashMap<>();

		for (BrewingPlace place : BrewingPlace.values())
		{
			int newValue = client.getVar(place.getVarbit());
			Integer oldData = brewingData.get(place);
			int oldValue = oldData == null ? -1 : oldData;

			if (newValue != oldValue)
			{
				newData.put(place, newValue);
				changed = true;
			}
		}
		if (changed)
		{
			brewingData.putAll(newData);
			allComplete = brewingData.values().stream().map(BrewingState::of).allMatch(state -> state == BrewingState.COMPLETE || state == BrewingState.MATURE_COMPLETE);
			anyComplete = brewingData.values().stream().map(BrewingState::of).anyMatch(state -> state == BrewingState.COMPLETE || state == BrewingState.MATURE_COMPLETE);
		}
		return changed;
	}

	/**
	 * Checks if the vats have finished brewing and send a notification if required
	 *
	 */
	public boolean checkCompletion()
	{
		if (summary == SummaryState.IN_PROGRESS && allComplete)
		{
			summary = SummaryState.COMPLETED;

			if (config.brewingNotification())
			{
				notifier.notify("Your vats are ready to be emptied");
			}

			return true;
		}

		return false;
	}
}
