package net.runelite.client.plugins.timetracking.cooking;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;

@AllArgsConstructor
@Getter
enum Brew
{
	//STATES: Ingredients, yeast, turned, turned and matured, done, done and matured (went bad). ORDER IS PROBABLY NOT CORRECT!!!
	CIDER("Cider", ItemID.CIDER, null),
	DWARWEN_STOUT("Dwarven Stout", ItemID.DWARVEN_STOUT, null),
	ASGARNIAN_ALE("Asgarnian ale", ItemID.ASGOLDIAN_ALE, null),
	GREENMANS_ALE("Greenman's ale", ItemID.GREENMANS_ALE, null),
	WIZARDS_MIND_BOMB("Wizard's mind bomb", ItemID.WIZARDS_MIND_BOMB, null),
	DRAGON_BITTER("Dragon bitter", ItemID.DRAGON_BITTER, Arrays.asList(28, 29, 30, 31)),
	MOONLIGHT_MEAD("Moonlight mead", ItemID.MOONLIGHT_MEAD, null),
	AXEMANS_FOLLY("Axeman's folly", ItemID.AXEMANS_FOLLY, Arrays.asList(40, 41, 42, 43)),
	CHEFS_DELIGHT("Chef's delight", ItemID.CHEFS_DELIGHT, Arrays.asList(46, 47, 48, 49)),
	SLAYERS_RESPITE("Slayer's respite", ItemID.SLAYERS_RESPITE, Arrays.asList(52, 53, 54, 55));

	private static final Map<Integer, Brew> BREWS;

	static
	{
		ImmutableMap.Builder<Integer, Brew> builder = new ImmutableMap.Builder<>();
		for (Brew brew : values())
		{
			if (brew.values == null) continue;
			for (int val : brew.values)
			{
				builder.put(val, brew);
			}
		}
		BREWS = builder.build();
	}

	/**
	 * Gets the {@code Brew} corresponding to the given {@code VarBits} value.
	 */
	@Nullable
	static Brew getBrew(int value)
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
	private final List<Integer> values;


}
