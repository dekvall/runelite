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
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.timetracking.SummaryState;
import net.runelite.client.plugins.timetracking.TimeTrackingConfig;

@Singleton
public class BrewingTracker
{
	static final int BREWING_STATES = 2;

	private final Client client;
	private final ItemManager itemManager;
	private final ConfigManager configManager;
	private final TimeTrackingConfig config;
	private final Notifier notifier;

	@Getter(AccessLevel.PACKAGE)
	private final ConcurrentMap<BrewingPlace, BrewingData> brewingData = new ConcurrentHashMap<>();

	@Getter
	private SummaryState summary = SummaryState.UNKNOWN;

	@Inject
	private BrewingTracker(Client client, ItemManager itemManager, ConfigManager configManager, TimeTrackingConfig config, Notifier notifier)
	{
		this.client = client;
		this.itemManager = itemManager;
		this.configManager = configManager;
		this.config = config;
		this.notifier = notifier;
	}

	public BrewingTabPanel createBrewingTabPanel()
	{
		return new BrewingTabPanel(itemManager, this, config);
	}

	public void loadFromConfig()
	{
		brewingData.clear();

		final String group = TimeTrackingConfig.CONFIG_GROUP + "." + client.getUsername() + "." + TimeTrackingConfig.BREWING;

		for (BrewingPlace place : BrewingPlace.values())
		{
			String key = Integer.toString(place.getVarbit().getId());
			String storedValue = configManager.getConfiguration(group, key);

			if (storedValue != null)
			{
				try
				{
					int value = Integer.parseInt(storedValue);
					brewingData.put(place, new BrewingData(place, value));
				}
				catch (NumberFormatException e)
				{
					// ignored
				}
			}
		}
	}

	/**
	 * Updates tracker data if player is within range of any vats. Returns true if any data was changed.
	 */
	public boolean updateData(WorldPoint location)
	{
		boolean changed = false;
		final Map<BrewingPlace, BrewingData> newData = new HashMap<>();

		for (BrewingPlace place : BrewingPlace.values())
		{
			int newValue = client.getVar(place.getVarbit());
			BrewingData oldData = brewingData.get(place);
			int oldValue = oldData == null ? -1 : oldData.getValue();
			if (newValue != oldValue)
			{
				newData.put(place, new BrewingData(place, newValue));
				changed = true;
			}
		}
		if (changed)
		{
			brewingData.putAll(newData);
			saveToConfig(newData);
		}
		return changed;
	}

	/**
	 * Checks if the bird houses have become ready to be dismantled,
	 * and sends a notification if required.
	 */
	public boolean checkCompletion()
	{
		if (summary == SummaryState.IN_PROGRESS && brewingData.values().stream().allMatch(data -> BrewingState.getState(data.getValue()) == BrewingState.COMPLETE))
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

	private void saveToConfig(Map<BrewingPlace, BrewingData> updatedData)
	{
		final String group = TimeTrackingConfig.CONFIG_GROUP + "." + client.getUsername() + "." + TimeTrackingConfig.BREWING;

		for (BrewingData data : updatedData.values())
		{
			String key = Integer.toString(data.getVat().getVarbit().getId());
			configManager.setConfiguration(group, key, data.getVat().getVarbit());
		}
	}
}
