package net.runelite.client.plugins.timetracking.cooking;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;

@Getter
enum Brew
{	//In order: Ingredients Added, Yeast added, Set, Complete, Complete and Mature
	CIDER("Cider", ItemID.CIDER, 58, 59, 60, 61), //Cider has no mature phase
	DWARWEN_STOUT("Dwarven Stout", ItemID.DWARVEN_STOUT, 4, 5, 6, 7, 8),
	ASGARNIAN_ALE("Asgarnian ale", ItemID.ASGOLDIAN_ALE, 10, 11, 12, 13, 14),
	GREENMANS_ALE("Greenman's ale", ItemID.GREENMANS_ALE, 16, 17, 18, 19, 20),
	WIZARDS_MIND_BOMB("Wizard's mind bomb", ItemID.WIZARDS_MIND_BOMB, 22, 23, 24, 25, 26),
	DRAGON_BITTER("Dragon bitter", ItemID.DRAGON_BITTER, 28, 29, 30, 31, 32),
	MOONLIGHT_MEAD("Moonlight mead", ItemID.MOONLIGHT_MEAD, 34, 35, 36, 37, 38),
	AXEMANS_FOLLY("Axeman's folly", ItemID.AXEMANS_FOLLY,40, 41, 42, 43, 44),
	CHEFS_DELIGHT("Chef's delight", ItemID.CHEFS_DELIGHT, 46, 47, 48, 49, 50),
	SLAYERS_RESPITE("Slayer's respite", ItemID.SLAYERS_RESPITE, 52, 53, 54, 55, 56);

	private static final Map<Integer, Brew> BREWS;

	static
	{
		ImmutableMap.Builder<Integer, Brew> builder = new ImmutableMap.Builder<>();
		for (Brew brew : values())
		{
			for (int val : brew.values)
			{
				builder.put(val, brew);
			}
		}
		BREWS = builder.build();
	}

	Brew(String name, int itemID, int... values)
	{
		this.name = name;
		this.itemID = itemID;
		this.values = values;
	}

	/**
	 * Gets the {@code Brew} corresponding to the given {@code VarBits} value.
	 */
	@Nullable
	static Brew of(int value)
	{
		return BREWS.get(value);
	}
	/**
	 * User-visible name
	 */
	private final String name;

	/**
	 * User-visible item icon
	 */
	private final int itemID;

	/**
	 * VarbitValues for the brew
	 */
	private final int[] values;


}
