/*
 * Copyright (c) 2019, Dekvall <https://github.com/dekvall>
 * Copyright (c) 2019, Abex
 * Copyright (c) 2019, Psikoi <https://github.com/psikoi>
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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.runelite.api.ItemID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.timetracking.TabContentPanel;
import net.runelite.client.plugins.timetracking.TimeTrackingConfig;
import net.runelite.client.plugins.timetracking.TimeablePanel;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.DynamicGridLayout;

public class BrewingTabPanel extends TabContentPanel
{
	private static final Color COMPLETED_COLOR = ColorScheme.PROGRESS_COMPLETE_COLOR.darker();

	private final ItemManager itemManager;
	private final BrewingTracker brewingTracker;
	private final TimeTrackingConfig config;
	private final List<TimeablePanel<BrewingPlace>> spacePanels;

	BrewingTabPanel(ItemManager itemManager, BrewingTracker brewingTracker, TimeTrackingConfig config)
	{
		this.itemManager = itemManager;
		this.brewingTracker = brewingTracker;
		this.config = config;
		this.spacePanels = new ArrayList<>();

		setLayout(new DynamicGridLayout(0, 1, 0, 0));
		setBackground(ColorScheme.DARK_GRAY_COLOR);

		boolean first = true;
		for (BrewingPlace space : BrewingPlace.values())
		{
			TimeablePanel<BrewingPlace> panel = new TimeablePanel<>(space, space.getName(), BrewingTracker.BREWING_STATES);

			spacePanels.add(panel);
			add(panel);

			// remove the top border on the first panel
			if (first)
			{
				first = false;
				panel.setBorder(null);
			}
		}
	}

	@Override
	public int getUpdateInterval()
	{
		return 50; // 10 seconds
	}

	@Override
	public void update()
	{
		for (TimeablePanel<BrewingPlace> panel : spacePanels)
		{
			BrewingPlace space = panel.getTimeable();
			BrewingData data = brewingTracker.getBrewingData().get(space);
			int value = -1;

			if (data != null)
			{
				value = data.getValue();
			}

			Brew brew = Brew.getBrew(value);
			BrewingState state = BrewingState.getState(value);

			if (brew == null)
			{
				itemManager.getImage(ItemID.BEER_GLASS).addTo(panel.getIcon());
				panel.getProgress().setVisible(false);
			}
			else
			{
				itemManager.getImage(brew.getItemID()).addTo(panel.getIcon());
				panel.getIcon().setToolTipText(brew.getName());
				panel.getProgress().setVisible(true);
			}

			panel.getProgress().setForeground(state.getColor().darker());

			switch (state)
			{
				case EMPTY:
					panel.getIcon().setToolTipText("Empty");
					panel.getEstimate().setText("Empty");
					break;
				case WATER_ADDED:
					panel.getIcon().setToolTipText("Water");
					panel.getEstimate().setText("Water");
					break;
				case BARLEY_MALT_ADDED:
					panel.getIcon().setToolTipText("Barley malt");
					panel.getEstimate().setText("Barely malt");
					break;
				case INGREDIENTS_ADDED:
					panel.getIcon().setToolTipText("Ingredients added");
					panel.getEstimate().setText("Ingredients added");
					break;
				case YEAST_ADDED:
					panel.getProgress().setValue(0);
					panel.getEstimate().setText("Yeast added");
					break;
				case SET:
					panel.getProgress().setValue(1);
					panel.getEstimate().setText("Turned");
					break;
				case COMPLETE:
					panel.getProgress().setValue(2);
					panel.getProgress().setForeground(COMPLETED_COLOR);
					panel.getEstimate().setText("Complete");
				default:
					panel.getIcon().setToolTipText("Unknown state");
					panel.getEstimate().setText("Unknown");
					break;
			}
		}
	}
}
