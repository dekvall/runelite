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
	private boolean ready;
	private Instant kittenSpawned;
	private Instant catSpawned;
	private Instant kittenFed;
	private Instant kittenAttention;
	private int timeSpendGrowing;
	private int timeNeglected;
	private int timeHungry;
	private static final String CHAT_CAT_STROKE = "That cat sure loves to be stroked.";
	private static final String CHAT_CAT_BALLOFWOOL = "That kitten loves to play with that ball of wool. I think itis its favourite.";
	private static final String CHAT_CAT_GROWN = "Your kitten has grown into a healthy cat that can hunt for itself.";
	private static final String CHAT_CAT_OVERGROWN = "Your cat has grown into a mighty feline, but it will no longer be ableto chase vermin.";
	private int followerID = 0;
	private int previousFeline = 0;
	private int followerVarbit = 0;
	private boolean cat = false; // npcId 1619-1625
	private boolean lazycat = false; // npcId 1626-1632
	private boolean wilycat = false; // npcId 5584-5590
	private boolean kitten = false; // npcId 5591-5597
	private boolean overgrown = false; // npcId 5598-5604
	private boolean nonFeline = false;

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
		previousFeline = config.felineId();
	}

	@Override
	protected void shutDown() throws Exception
	{
		byeFollower();
	}

	private void checkForFollower()
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		if (playerHasFollower())
		{
			newFollower();
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if (client.getVar(VarPlayer.FOLLOWER) == followerVarbit)
		{
			return; //VarPlayer.FOLLOWER did not change, exit
		}

		followerVarbit = client.getVar(VarPlayer.FOLLOWER);

		if (playerHasFollower() & followerID == 0) // player gained a follower
		{
			getFollowerID();
		}

		if (playerHasFollower() & followerID != 0) // follower changed ID
		{
			getFollowerID();
		}

		if (!playerHasFollower() & followerID != 0) // player lost it's follower
		{
			byeFollower();
		}
	}

	private void getFollowerID()
	{
		String s = Integer.toBinaryString(client.getVar(VarPlayer.FOLLOWER));

		while (s.length() < 32)
		{
			s = "0" + s;
		}

		int previousFollowerID = 0;

		if (followerID != 0)
		{
			previousFollowerID = followerID;
		}

		// followerID is the first 2 octets converted into decimals
		followerID = Integer.parseInt(s.substring(0, 16), 2);

		if (followerID == previousFollowerID)
		{
			return;
		}

		if (followerID != 0) //Varbit needs to fill up first after logging in
		{
			newFollower();
		}
	}

	private void newFollower()
	{
		final Feline feline = Feline.of(followerID);

		if (feline == null)
		{
			return;
		}

		previousFeline = config.felineId();

		if (feline.getType() == FelineType.KITTEN)
		{
			if (followerID == previousFeline) // The same kitten is back!
			{
				timeSpendGrowing = config.secondsAlive();
				timeNeglected = config.secondsNeglected();
				timeHungry = config.secondsHungry();
				kittenSpawned = Instant.now();

				addKittenGrowthBox((11790 - timeSpendGrowing));
				addHungryTimer(1800 - timeHungry);
				addAttentionTimer(1800 - timeNeglected);
			}

			if (followerID != previousFeline) // new kitten, new timer
			{
				config.secondsAlive(0);
				config.secondsHungry(0);
				config.secondsNeglected(0);
				timeSpendGrowing = 0;
				timeNeglected = 0;
				timeHungry = 0;

				kittenSpawned = Instant.now();
				kittenFed = Instant.now();
				kittenAttention = Instant.now();
				addKittenGrowthBox(11790);
				addHungryTimer(1980);
				addAttentionTimer(2100);
				saveGrowthProgress();
			}
		}

		if (cat == true)
		{
			if (followerID == previousFeline) // The same cat is back!
			{
				catSpawned = Instant.now();
				timeSpendGrowing = config.secondsAlive();
				addKittenGrowthBox((11400 - timeSpendGrowing));
			}

			if (followerID != previousFeline) // new cat, new timer
			{
				timeSpendGrowing = 0;
				catSpawned = Instant.now();
				addKittenGrowthBox(11400);
				saveGrowthProgress();
			}
		}
	}

	private void byeFollower()
	{
		if (kitten == true)
		{
			saveGrowthProgress();
			kittenSpawned = null;
			kittenFed = null;
			kittenAttention = null;
		}

		if (cat == true)
		{
			saveGrowthProgress();
			catSpawned = null;
		}

		infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
		infoBoxManager.removeIf(t -> t instanceof KittenHungryTimer);
		infoBoxManager.removeIf(t -> t instanceof KittenAttentionTimer);

		followerID = 0;
		cat = false;
		lazycat = false;
		wilycat = false;
		kitten = false;
		overgrown = false;
		nonFeline = false;
	}

	private void saveGrowthProgress()
	{
		if (kitten == true)
		{
			config.felineId(followerID);

			Duration timeAlive = Duration.between(kittenSpawned, Instant.now());
			int secondsAlive = toIntExact(timeAlive.getSeconds());
			config.secondsAlive(timeSpendGrowing + secondsAlive);

			if (kittenFed != null)
			{
				Duration timeFed = Duration.between(kittenFed, Instant.now());
				int secondsFed = toIntExact(timeFed.getSeconds());
				config.secondsHungry(secondsFed);
			}

			if (kittenFed == null)
			{
				//kitten was not fed, so we add the time it has been out to the time it was already hungry for
				Duration timeSinceSpawn = Duration.between(kittenSpawned, Instant.now());
				int secondsSinceSpawn = toIntExact(timeSinceSpawn.getSeconds());
				config.secondsHungry(timeHungry + secondsSinceSpawn);
			}

			if (kittenAttention != null)
			{
				Duration timeAttention = Duration.between(kittenAttention, Instant.now());
				int secondsAttention = toIntExact(timeAttention.getSeconds());
				config.secondsNeglected(secondsAttention);
			}

			if (kittenAttention == null)
			{
				//kitten was not paid attention, so we add the time it has been out to the time it was already neglected for
				Duration timeSinceSpawn = Duration.between(kittenSpawned, Instant.now());
				int secondsSinceSpawn = toIntExact(timeSinceSpawn.getSeconds());
				config.secondsNeglected(timeNeglected + secondsSinceSpawn);
			}
		}

		if (cat == true)
		{
			config.felineId(followerID);
			Duration timeAlive = Duration.between(catSpawned, Instant.now());
			int secondsAlive = toIntExact(timeAlive.getSeconds());
			config.secondsAlive(timeSpendGrowing + secondsAlive);
		}
	}


	private void addKittenGrowthBox(int seconds)
	{
		if (seconds <= 0 )
		{
			return;
		}

		Feline feline = Feline.of(followerID);

		if (feline == null)
		{
			return;
		}

		infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
		KittenGrowthTimer timer = new KittenGrowthTimer(feline, itemManager.getImage(feline.getItemSpriteId()), this, Duration.ofSeconds(seconds));

		if (kitten == true)
		{
			if (config.kittenInfoBox())
			{
				timer.setTooltip("Approximate time left to grow into a cat");
				infoBoxManager.addInfoBox(timer);
			}
		}

		if (cat == true)
		{
			if (config.catInfoBox())
			{
				timer.setTooltip("Approximate time left to transform into an overgrown cat");
				infoBoxManager.addInfoBox(timer);
			}
		}
	}

	private void addHungryTimer(long period, ChronoUnit unit)
	{
		infoBoxManager.removeIf(t -> t instanceof KittenTimer);
		KittenTimer timer = new KittenTimer(period, unit, itemManager.getImage(ItemID.SEASONED_SARDINE), "time until your kitten leaves you for being underfed", this);
		if (config.kittenHungryBox() & kitten == true)
		{
			infoBoxManager.addInfoBox(timer);
		}
	}

	private void addAttentionTimer(long period, ChronoUnit unit)
	{
		infoBoxManager.removeIf(t -> t instanceof KittenTimer);
		KittenTimer timer = new KittenTimer(period, unit, itemManager.getImage(ItemID.BALL_OF_WOOL), "Approximate time until your kitten leaves you for being neglectful", this);

		if (config.kittenAttentionBox() & kitten == true)
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
				resetKitten();
				break;
			default:
				return;
		}
	}
	
	@Subscribe
	public void onGameTick(GameTick tick)
	{
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

		final Widget notificationDialog = client.getWidget(WidgetInfo.DIALOG_NOTIFICATION_TEXT);
		if (notificationDialog != null)
		{
			final String notificationText = Text.removeTags(notificationDialog.getText());
			if (notificationText.equals(CHAT_CAT_GROWN))
			{
				kitten = false;
				infoBoxManager.removeIf(t -> t instanceof KittenAttentionTimer);
				infoBoxManager.removeIf(t -> t instanceof KittenHungryTimer);
			}
			if (notificationText.equals(CHAT_CAT_OVERGROWN))
			{
				cat = false;
				infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
				getFollowerID();
			}
		}
	}

	private void resetKitten()
	{
		kittenSpawned = null;
		kittenFed = null;
		kittenAttention = null;
		previousFeline = 0;
		config.felineId(0); // in case the new kitten has the same NpcID. We need to track growth progress from the beginning.

		infoBoxManager.removeIf(t -> t instanceof KittenTimer);

		followerID = 0;
		cat = false;
		lazycat = false;
		wilycat = false;
		kitten = false;
		overgrown = false;
		nonFeline = false;
	}

	private void sendNotification(String message)
	{
		if (config.kittenNotifications())
		{
			notifier.notify(message);
		}
	}

	@Subscribe
	private void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("kittenConfig"))
		{
			return;
		}

		if (event.getKey().equals("kittenInfoBox"))
		{
			if (event.getNewValue().equals("true"))
			{
				if (kitten == true)
				{
					timeSpendGrowing = config.secondsAlive();
					Duration timeAlive = Duration.between(kittenSpawned, Instant.now());
					int secondsAlive = toIntExact(timeAlive.getSeconds());
					addKittenGrowthBox(14400 - secondsAlive - timeSpendGrowing);
				}
			}
			if (event.getNewValue().equals("false"))
			{
				if (kitten == true)
				{
					infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
				}
			}
		}

		if (event.getKey().equals("catInfoBox"))
		{
			if (event.getNewValue().equals("true"))
			{
				if (cat == true)
				{
					timeSpendGrowing = config.secondsAlive();
					Duration timeAlive = Duration.between(catSpawned, Instant.now());
					int secondsAlive = toIntExact(timeAlive.getSeconds());
					addKittenGrowthBox(14400 - secondsAlive - timeSpendGrowing);
				}
			}
			if (event.getNewValue().equals("false"))
			{
				if (cat == true)
				{
					infoBoxManager.removeIf(t -> t instanceof KittenGrowthTimer);
				}
			}
		}

		if (event.getKey().equals("kittenAttentionBox"))
		{
			if (event.getNewValue().equals("true"))
			{
				if (kittenAttention != null)
				{
					Duration timeAttention = Duration.between(kittenAttention, Instant.now());
					int secondsAttention = toIntExact(timeAttention.getSeconds());
					addAttentionTimer(1800 - secondsAttention);
				}
				if (kittenAttention == null)
				{
					timeNeglected = config.secondsNeglected();
					Duration timeSinceSpawn = Duration.between(kittenSpawned, Instant.now());
					int secondsAttention = toIntExact(timeSinceSpawn.getSeconds());
					addAttentionTimer(1800 - timeNeglected - secondsAttention);
				}
			}
			if (event.getNewValue().equals("false"))
			{
				infoBoxManager.removeIf(t -> t instanceof KittenAttentionTimer);
			}
		}

		if (event.getKey().equals("kittenHungryBox"))
		{
			if (event.getNewValue().equals("true"))
			{
				if (kittenFed != null)
				{
					Duration timeHungry = Duration.between(kittenFed, Instant.now());
					int secondsHungry = toIntExact(timeHungry.getSeconds());
					addHungryTimer(1800 - secondsHungry);
				}

				if (kittenFed == null)
				{
					timeHungry = config.secondsHungry();
					Duration timeSinceSpawn = Duration.between(kittenSpawned, Instant.now());
					int secondsSinceSpawn = toIntExact(timeSinceSpawn.getSeconds());
					addHungryTimer(1800 - timeHungry - secondsSinceSpawn);
				}
			}

			if (event.getNewValue().equals("false"))
			{
				infoBoxManager.removeIf(t -> t instanceof KittenHungryTimer);
			}
		}
	}

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
				byeFollower();
		}
	}

	public boolean playerHasFollower()
	{
		return (client.getVar(VarPlayer.FOLLOWER)) != -1;
	}
}
