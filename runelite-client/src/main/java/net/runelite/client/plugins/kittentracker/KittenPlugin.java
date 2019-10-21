/*
 * Copyright (c) 2018, Nachtmerrie <https://github.com/Nachtmerrie>
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

import static java.lang.Math.toIntExact;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.Notifier;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.Text;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@PluginDescriptor(
	name = "Kitten"
)
public class KittenPlugin extends Plugin
{
	private static final String CHAT_CAT_STROKE = "That cat sure loves to be stroked.";
	private static final String CHAT_CAT_BALLOFWOOL = "That kitten loves to play with that ball of wool. I think itis its favourite.";

	private Instant followerSpawned;
	private int currentFollowerVarbit;
	private boolean ready;
	private Instant kittenFed;
	private Instant kittenAttention;
	private int timeSpentGrowing;
	private int timeNeglected;
	private int timeHungry;
	private int currentFollowerID = -1;
	private Feline currentFeline = null;
	private int previousFelineID = -1;

	@Provides
	KittenConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(KittenConfig.class);
	}

	@Inject
	private Client client;

	@Inject
	private Notifier notifier;

	@Inject
	private KittenConfig config;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Override
	public void startUp()
	{
		clientThread.invokeLater(this::checkForFollower);
		previousFelineID = config.felineId();
	}

	@Override
	protected void shutDown() throws Exception
	{
		saveGrowthProgress();
		resetFollower();
	}

	private void checkForFollower()
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		updateFollower();
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		final int followerVarbit = client.getVar(VarPlayer.FOLLOWER);

		if (followerVarbit == currentFollowerVarbit)
		{
			return;
		}

		currentFollowerVarbit = followerVarbit;

		// followerID is the first two octets of the varp.
		currentFollowerID = (currentFollowerVarbit >> 16) & 0xffff;
		updateFollower();
	}

	private void updateFollower()
	{
		if (currentFollowerVarbit != -1)
		{
			newFollower();
		}
		else
		{
			saveGrowthProgress();
			resetFollower();
		}
	}

	private void newFollower()
	{
		currentFeline = Feline.of(currentFollowerID);

		if (currentFeline == null)
		{
			return;
		}

		previousFelineID = config.felineId();

		if (currentFeline.getType() == FelineType.KITTEN)
		{
			if (currentFollowerID == previousFelineID) // The same kitten is back!
			{
				timeSpentGrowing = config.secondsAlive();
				timeNeglected = config.secondsNeglected();
				timeHungry = config.secondsHungry();
				followerSpawned = Instant.now();

				updateKittenGrowthBox(11790 - timeSpentGrowing, ChronoUnit.SECONDS);
				addHungryTimer(1800 - timeHungry, ChronoUnit.SECONDS);
				addAttentionTimer(1800 - timeNeglected, ChronoUnit.SECONDS);
			}
			else
			{
				//Fresh kitty
				config.secondsAlive(0);
				config.secondsHungry(0);
				config.secondsNeglected(0);
				timeSpentGrowing = 0;
				timeNeglected = 0;
				timeHungry = 0;

				followerSpawned = Instant.now();
				kittenFed = Instant.now();
				kittenAttention = Instant.now();
				updateKittenGrowthBox(11790, ChronoUnit.SECONDS);
				addHungryTimer(1980, ChronoUnit.SECONDS);
				addAttentionTimer(2100, ChronoUnit.SECONDS);
				saveGrowthProgress();
			}
		}
		else if (currentFeline.getType() == FelineType.CAT)
		{
			if (currentFollowerID == previousFelineID) // The same cat is back!
			{
				followerSpawned = Instant.now();
				timeSpentGrowing = config.secondsAlive();
				updateKittenGrowthBox(11400 - timeSpentGrowing, ChronoUnit.SECONDS);
			}

			if (currentFollowerID != previousFelineID) // new cat, new timer
			{
				timeSpentGrowing = 0;
				followerSpawned = Instant.now();
				updateKittenGrowthBox(11400, ChronoUnit.SECONDS);
				saveGrowthProgress();
			}
		}
	}

	private void saveGrowthProgress()
	{
		if (currentFeline == null)
		{
			return;
		}

		config.felineId(currentFollowerID);
		Duration timeAlive = Duration.between(followerSpawned, Instant.now());
		int secondsAlive = toIntExact(timeAlive.getSeconds());
		config.secondsAlive(timeSpentGrowing + secondsAlive);

		if (currentFeline.getType() == FelineType.KITTEN)
		{
			if (kittenFed != null)
			{
				Duration timeFed = Duration.between(kittenFed, Instant.now());
				int secondsFed = toIntExact(timeFed.getSeconds());
				config.secondsHungry(secondsFed);
			}
			else
			{
				config.secondsHungry(timeHungry + secondsAlive);
			}

			if (kittenAttention != null)
			{
				Duration timeAttention = Duration.between(kittenAttention, Instant.now());
				int secondsAttention = toIntExact(timeAttention.getSeconds());
				config.secondsNeglected(secondsAttention);
			}
			else
			{
				config.secondsNeglected(timeNeglected + secondsAlive);
			}
		}
	}

	private void updateKittenGrowthBox(long period, ChronoUnit unit)
	{
		final Feline feline = Feline.of(currentFollowerID);

		if (feline == null)
		{
			return;
		}

		infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);

		final String tooltip;

		switch (feline.getType())
		{
			case KITTEN:
				tooltip = "Approximate time left to grow into a cat";
				break;
			case CAT:
				tooltip = "Approximate time left to transform into an overgrown cat";
				break;
			default:
				return;
		}

		final KittenGrowthTimer timer = new KittenGrowthTimer(period, unit, itemManager.getImage(feline.getItemSpriteId()), tooltip, this);
		infoBoxManager.addInfoBox(timer);
	}

	private void addHungryTimer(long period, ChronoUnit unit)
	{
		infoBoxManager.removeIf(t -> t instanceof KittenTimer);
		KittenTimer timer = new KittenTimer(period, unit, itemManager.getImage(ItemID.SEASONED_SARDINE), "time until your kitten leaves you for being underfed", this);
		if (config.kittenHungryBox())
		{
			infoBoxManager.addInfoBox(timer);
		}
	}

	private void addAttentionTimer(long period, ChronoUnit unit)
	{
		infoBoxManager.removeIf(t -> t instanceof KittenTimer);
		KittenTimer timer = new KittenTimer(period, unit, itemManager.getImage(ItemID.BALL_OF_WOOL), "Approximate time until your kitten leaves you for being neglectful", this);

		if (config.kittenAttentionBox())
		{
			infoBoxManager.addInfoBox(timer);
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		final String message = Text.removeTags(event.getMessage());

		switch (message)
		{
			case "The kitten gobbles up the fish.":
			case "The kitten laps up the milk.":
				kittenFed = Instant.now();
				addHungryTimer(30, ChronoUnit.MINUTES);
				break;
			case "Your kitten is hungry.":
				sendNotification(message);
				addHungryTimer(5, ChronoUnit.MINUTES);
				break;
			case "Your kitten is very hungry.":
				sendNotification(message);
				addHungryTimer(2, ChronoUnit.MINUTES);
				break;
			case "Your kitten wants attention.":
				sendNotification(message);
				addAttentionTimer(5, ChronoUnit.MINUTES);
				break;
			case "Your kitten really wants attention.":
				sendNotification(message);
				addAttentionTimer(2, ChronoUnit.MINUTES);
				break;
			case "Your kitten got lonely and ran off.":
			case "The cat has run away.":
				sendNotification(message);
				resetFollower();
				break;
			default:
				return;
		}
	}
	
	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if (event.getGroupId() != WidgetID.DIALOG_PLAYER_GROUP_ID)
		{
			return;
		}

		final Widget playerDialog = client.getWidget(WidgetInfo.DIALOG_PLAYER_TEXT);

		if (playerDialog != null)
		{
			final String playerText = Text.removeTags(playerDialog.getText());
			if (playerText.equals(CHAT_CAT_STROKE))
			{
				kittenAttention = Instant.now();
				addAttentionTimer(30, ChronoUnit.MINUTES);
			}
			else if (playerText.equals(CHAT_CAT_BALLOFWOOL))
			{
				kittenAttention = Instant.now();
				addAttentionTimer(1, ChronoUnit.HOURS);
			}
		}
	}

	private void resetFollower()
	{
		followerSpawned = null;
		kittenFed = null;
		kittenAttention = null;
		previousFelineID = -1;
		config.felineId(-1); // in case the new kitten has the same NpcID. We need to track growth progress from the beginning.

		infoBoxManager.removeIf(t -> t instanceof KittenTimer);
		infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);

		currentFollowerID = -1;
		currentFeline = null;
	}

	private void sendNotification(String message)
	{
		if (config.kittenNotifications())
		{
			notifier.notify(message);
		}
	}

//	@Subscribe
//	private void onConfigChanged(ConfigChanged event)
//	{
//		if (!event.getGroup().equals("kittenConfig"))
//		{
//			return;
//		}
//
//		if (event.getKey().equals("kittenInfoBox"))
//		{
//			if (event.getNewValue().equals("true"))
//			{
//				if (kitten == true)
//				{
//					timeSpendGrowing = config.secondsAlive();
//					Duration timeAlive = Duration.between(kittenSpawned, Instant.now());
//					int secondsAlive = toIntExact(timeAlive.getSeconds());
//					updateKittenGrowthBox(14400 - secondsAlive - timeSpendGrowing, ChronoUnit.SECONDS);
//				}
//			}
//			if (event.getNewValue().equals("false"))
//			{
//				if (kitten == true)
//				{
//					infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
//				}
//			}
//		}
//
//		if (event.getKey().equals("catInfoBox"))
//		{
//			if (event.getNewValue().equals("true"))
//			{
//				if (cat == true)
//				{
//					timeSpendGrowing = config.secondsAlive();
//					Duration timeAlive = Duration.between(catSpawned, Instant.now());
//					int secondsAlive = toIntExact(timeAlive.getSeconds());
//					updateKittenGrowthBox(14400 - secondsAlive - timeSpendGrowing, ChronoUnit.SECONDS);
//				}
//			}
//			if (event.getNewValue().equals("false"))
//			{
//				if (cat == true)
//				{
//					infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
//				}
//			}
//		}
//
//		if (event.getKey().equals("kittenAttentionBox"))
//		{
//			if (event.getNewValue().equals("true"))
//			{
//				if (kittenAttention != null)
//				{
//					Duration timeAttention = Duration.between(kittenAttention, Instant.now());
//					int secondsAttention = toIntExact(timeAttention.getSeconds());
//					addAttentionTimer(1800 - secondsAttention, ChronoUnit.SECONDS);
//				}
//				if (kittenAttention == null)
//				{
//					timeNeglected = config.secondsNeglected();
//					Duration timeSinceSpawn = Duration.between(kittenSpawned, Instant.now());
//					int secondsAttention = toIntExact(timeSinceSpawn.getSeconds());
//					addAttentionTimer(1800 - timeNeglected - secondsAttention, ChronoUnit.SECONDS);
//				}
//			}
//			if (event.getNewValue().equals("false"))
//			{
//				infoBoxManager.removeIf(t -> t instanceof KittenAttentionTimer);
//			}
//		}
//
//		if (event.getKey().equals("kittenHungryBox"))
//		{
//			if (event.getNewValue().equals("true"))
//			{
//				if (kittenFed != null)
//				{
//					Duration timeHungry = Duration.between(kittenFed, Instant.now());
//					int secondsHungry = toIntExact(timeHungry.getSeconds());
//					addHungryTimer(1800 - secondsHungry, ChronoUnit.SECONDS);
//				}
//
//				if (kittenFed == null)
//				{
//					timeHungry = config.secondsHungry();
//					Duration timeSinceSpawn = Duration.between(kittenSpawned, Instant.now());
//					int secondsSinceSpawn = toIntExact(timeSinceSpawn.getSeconds());
//					addHungryTimer(1800 - timeHungry - secondsSinceSpawn, ChronoUnit.SECONDS);
//				}
//			}
//
//			if (event.getNewValue().equals("false"))
//			{
//				infoBoxManager.removeIf(t -> t instanceof KittenHungryTimer);
//			}
//		}
//	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		GameState state = event.getGameState();
		switch (state)
		{
			case LOGGING_IN:
			case HOPPING:
				saveGrowthProgress();
			case CONNECTION_LOST:
				ready = true;
				break;
			case LOGGED_IN:
				if (ready)
				{
					ready = false;
				}
				break;
			case LOGIN_SCREEN:
				resetFollower();
		}
	}
}
