/*
 * Copyright (c) 2019, Dekvall <https://github.com/dekvall>
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
package net.runelite.client.plugins.skillguideitems;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Varbits;
import net.runelite.api.events.ConfigChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Skill Guide Items",
	description = "Show items used for various skilling methods",
	tags = {"skill", "magic", "crafting", "runes"}
)
public class SkillGuideItemsPlugin extends Plugin
{
	private static final String CONFIG_GROUP = "skillguideitems";

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private SkillGuideItemsConfig config;

	@Inject
	private SkillGuideItemsOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Provides
	SkillGuideItemsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillGuideItemsConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if (event.getGroupId() != WidgetID.SKILL_GUIDE_GROUP_ID)
		{
			return;
		}
		updateSpellRunes();

	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!CONFIG_GROUP.equals(event.getGroup()))
		{
			return;
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if (event.getIndex() != Varbits.SKILL_GUIDE_TAB.getId())
		{
			return;
		}
		int value = client.getVar(Varbits.SKILL_GUIDE_TAB);
		switch (value)
		{
			case 0:
			case 1:
			case 2:
			case 3:
				updateSpellRunes();
			default:
				return;
		}
	}

	private void updateSpellRunes()
	{
		final Widget skillguide = client.getWidget(WidgetID.SKILL_GUIDE_GROUP_ID, 7);
		for (Widget child : skillguide.getChildren())
		{
			String orig = child.getText();
			String top = orig.split("<br>")[0];
			Spell spell = Spell.getSpell(orig);
			if (spell != null)
			{
				System.out.println("Setting text");
				System.out.println(spell.getRunesString());
				System.out.println("Test");
				child.setText(top);
			}
		}
	}

}
