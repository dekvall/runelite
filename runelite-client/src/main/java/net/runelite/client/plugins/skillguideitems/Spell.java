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

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
enum Spell
{
	//Standard
	WIND_STRIKE("Wind Strike", 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	CONFUSE("Confusion", 3, 0, 0, 3, 2, 0, 1, 0,0,0,0, 0, 0,0, 0, 0, 0),
	WATER_STRIKE("Water Strike", 5, 0, 0, 0, 0, 0, 0, 0 ,0, 0,0, 0, 0,0, 0, 0, 0),
	LEVEL_1_ENCHANT("Lenvel 1 enchant", 7, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	EARTH_STRIKE("Weaken", 9, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WEAKEN("Weaken", 11, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FIRE_STRIKE("Fire Strike", 13, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BONES_TO_BANANAS("Bones to bananas", 15, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WIND_BOLT("Wind Bolt", 17, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CURSE("Curse", 19, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BIND("Bind", 20, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LOW_LEVEL_ALCHEMY("Low level alchemy", 21, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATER_BOLT("Water Bolt", 23, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	VARROCK_TELEPORT("Varrock teleport", 25, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LEVEL_2_ENCHANT("Level 2 enchant", 27, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	EARTH_BOLT("Earth Bolt", 29, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LUMBRIDGE_TELEPORT("Lumbridge teleport", 31, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	TELEKINETIC_GRAB("Telekinetic grab", 33, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FIRE_BOLT("Fire Bolt", 35, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FALADOR_TELEPORT("Falador teleport", 37, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CRUMBLE_UNDEAD("Crumble undead", 39, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HOUSE_TELEPORT("Teleport to house", 40, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WIND_BLAST("Wind Blast", 41, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SUPERHEAT_ITEM("Superheat item", 43, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CAMELOT_TELEPORT("Camelot teleport", 45, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATER_BLAST("Water Blast", 47, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LEVEL_3_ENCHANT("Level 3 enchant", 49, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	IBAN_BLAST("Iban blast", 50, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SNARE("Snare", 50, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	MAGIC_DART("Magic dart", 50, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ARDOUGNE_TELEPORT("Ardougne teleport", 51, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	EARTH_BLAST("Earth Blast", 53, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HIGH_LEVEL_ALCHEMY("High level alchemy", 55, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CHARGE_WATER_ORB("Charge water orb", 56, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LEVEL_4_ENCHANT("Level 4 enchant", 57, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATCHTOWER_TELEPORT("Watchtower teleport", 58, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FIRE_BLAST("Fire Blast", 59, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CHARGE_EARTH_ORB("Charge earth orb", 60, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BONES_TO_PEACHES("Bones to peaches", 60, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CLAWS_OF_GUTHIX("Claws of Guthix", 60, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FLAMES_OF_ZAMORAK("Flames of Zamorak", 60, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SARADOMIN_STRIKE("Saradomin Strike", 60, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	TROLLHEIM_TELEPORT("Trollheim teleport", 61, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WIND_WAVE("Wind Wave", 62, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CHARGE_FIRE_ORB("Charge fire orb", 63, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	APE_ATOLL_TELEPORT("Ape Atoll teleport", 64, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATER_WAVE("Water Wave", 65, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CHARGE_AIR_ORB("Charge air orb", 66, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	VULNERABILITY("Vulnerability", 66, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LEVEL_5_ENCHANT("Level 5 Enchant", 68, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	KOUREND_CASTLE_TELEPORT("Kourend Castle teleport", 69, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	EARTH_WAVE("Earth wave", 70, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ENFEEBLE("Enfeeble", 73, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LUMBRIDGE_TELEOTHER("Teleother Lumbridge", 74, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FIRE_WAVE("Fire Wave", 75, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ENTANGLE("Entangle", 79, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	STUN("Stun", 80, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CHARGE("Charge", 80, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WIND_SURGE("Wind Surge", 81, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FALADOR_TELEOTHER("Teleother Falador", 82, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATER_SURGE("Water Surge", 85, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	TELE_BLOCK("Tele block", 85, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BOUNTY_TELEPORT("Teleport to Bounty Target", 85, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LEVEL_6_ENCHANT("Level 6 enchant", 87, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	EARTH_SURGE("Earth Surge", 90, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CAMELOT_TELEOTHER("Teleother Camelot", 90, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LEVEL_7_ENCHANT("Level 7 enchant", 93, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FIRE_SURGE("Fire Surge", 95, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),

	//Ancients
	SMOKE_RUSH("Smoke rush", 50, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SHADOW_RUSH("Shadow rush", 52, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	PADDEWA_TELEPORT("Paddewwa teleport", 54, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BLOOD_RUSH("Blood rush", 56, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ICE_RUSH("Ice rush", 58, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SENNTISTEN_TELEPORT("Senntisten teleport", 60, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SMOKE_BURST("Smoke burst", 62, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SHADOW_BURST("Shadow burst",64, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	KHARYLL_TELEPORT("Kharyll teleport", 66, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BLOOD_BURST("Blood burst", 68, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ICE_BURST("Ice burst", 70, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LASSAR_TELEPORT("Lassar teleport", 72, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SMOKE_BLITZ("Smoke blitz", 74, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SHADOW_BLITZ("Shadow blitz", 76, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	DAREEYAK_TELEPORT("Dareeyak teleport", 78, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BLOOD_BLITZ("Blood blitz", 80, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ICE_BLITZ("Ice blitz", 82, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CARRALLANGAR_TELEPORT("Carrallangar teleport", 84, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	//BOUNTY TELE
	SMOKE_BARRAGE("Smoke barrage", 86, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SHADOW_BARRAGE("Shadow barrage", 88, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ANNAKARL_TELEPORT("Annakarl teleport", 90, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BLOOD_BARRAGE("Blood barrage", 92, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ICE_BARRAGE("Ice barrage", 94, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	GHORROCK_TELEPORT("Ghorrock teleport", 96, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),

	//Lunar spells
	BAKE_PIE("Bake pie", 65, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	GEOMANCY("Geomancy", 65, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CURE_PLANT("Cure plant", 66, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	MONSTER_EXAMINE("Monster examine", 66, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	NPC_CONTACT("NPC Contact", 67, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CURE_OTHER("Cure other", 68, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HUMIDIFY("Humidify", 68, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	MOONCLAN_TELEPORT("Moonclan teleport", 69, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	MOONCLAN_GROUP_TELEPORT("Tele group Moonclan", 70, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CURE_ME("Cure me", 71, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HUNTER_KIT("Hunter kit", 71, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	QURANIA_TELEPORT("Qurania teleport", 71, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATERBIRTH_TELEPORT("Waterbirth teleport", 72, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WATERBIRTH_GROUP_TELEPORT("Tele group waterbirth", 73, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CURE_GROUP("Cure group", 74, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	STAT_SPY("Stat spy", 75, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BARBARIAN_TELEPORT("Barbarian teleport", 75, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BARBARIAN_GROUP_TELEPORT("Tele group Barbarian", 76, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SPIN_FLAX("Spin Flax", 76, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SUPERGLASS_MAKE("Superglass make", 77, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	TAN_LEATHER("Tan Leather", 78, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	KHAZARD_TELEPORT("Khazard teleport", 78, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	KHAZARD_GROUP_TELEPORT("Tele group Khazard", 79, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	DREAM("Dream", 79, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	STRING_JEWELLERY("String jewellery", 80, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	RESTORE_SHARE("Stat restore pot share", 81, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	MAGIC_IMBUE("Magic imbue", 82, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FERTILE_SOIL("Fertile soil", 83, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BOOST_SHARE("Boost potion share", 84, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FISHING_GUILD_TELEPORT("Fishing guild teleport", 85, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	//BOUNTY TARGET
	FISHING_GUILD_GROUP_TELEPORT("Tele group fishing guild", 86, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	PLANK_MAKE("Plank make", 86, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CATHERBY_TELEPORT("Catherby teleport", 87, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CATHERBY_GROUP_TELEPORT("Tele group Catherby", 88, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	RECHARGE_DRAGONSTONE("Recharge Dragonstone", 89, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ICE_PLATEU_TELEPORT("Ice plateu teleport", 89, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ICE_PLATEU_GROUP_TELEPORT("Tele group Ice plateu", 90, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	ENERGY_TRANSFER("Energy transfer", 91, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HEAL_OTHER("Heal other", 92, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	VENGEANCE_OTHER("Vengeance other", 93, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	VENGEANCE("Vengeance", 94, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HEAL_GROUP("Heal group", 95, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SPELLBOOK_SWAP("Spellbook swap", 96, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),

	//Arceuus
	REANIMATE_GOBLIN("Reanimate goblins", 3, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	LUMBRIDGE_GRAVEYARD_TELEPORT("Lumbridge Graveyard teleport", 6, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_MONKEY("Reanimate monkeys", 7, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_IMP("Reanimate imps", 12, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_MINOTAUR("Reanimate minotaurs", 16, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	DRAYNOR_MANOR_TELEPORT("Draynor Manor teleport", 17, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_SCORPION("Reanimate scorpions", 19, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_BEAR("Reanimate bears", 21, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_UNICORN("Reanimate unicorns", 22, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BATTLEFRONT_TELEPORT("Battlefront teleport", 23, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_DOG("Reanimate dogs", 26, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	MIND_ALTAR_TELEPORT("Mind Altar teleport", 28, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_CHAOS_DRUID("Reanimate chaos druids", 30, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	RESPAWN_TELEPORT("Respawn teleport", 34, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_GIANT("Reanimate giants", 37, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	SALVE_GRAVEYARD_TELEPORT("Salve Graveyard teleport", 40, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_OGRE("Reanimate ogres", 40, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_ELF("Reanimate elves", 43, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_TROLL("Reanimate trolls", 46, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	FENKENSTRAINS_CASTLE_TELEPORT("Fenkenstrain's Castle teleport", 48, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_HORROR("Reanimate horrors", 52, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_KELPHITE("Reanimate kalphite", 57, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	WEST_ARDOUGNE_TELEPORT("West Ardougne teleport", 61, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_DAGANNOTH("Reanimate dagannoth", 62, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_BLOODVELD("Reanimate bloodveld", 65, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	HARMONY_ISLAND_TELEPORT("Harmony Island teleport", 65, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_TZHAAR("Reanimate TzHaar", 69, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	CEMETERY_TELEPORT("Cemetery teleport", 71, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_DEMON("Reanimate demons", 72, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_AVIANSIE("Reanimate aviansio", 78, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	RESURRECT_CROPS("Resurrect crops", 78, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	BARROWS_TELEPORT("Barrows teleport", 83, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_ABYSSAL_DEMON("Reanimate abyssal demons", 85, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	APE_ATOLL_TELEPORT_ARCEUUS("Ape Atoll teleport", 90, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0),
	REANIMATE_DRAGON("Reanimate dragons", 93, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0,0, 0, 0, 0);

	private final String name;
	private final int level;
	private final int airRuneCount;
	private final int mindRuneCount;
	private final int waterRuneCount;
	private final int earthRuneCount;
	private final int fireRuneCount;
	private final int bodyRuneCount;
	private final int cosmicRuneCount;
	private final int chaosRuneCount;
	private final int astralRuneCount;
	private final int natureRuneCount;
	private final int lawRuneCount;
	private final int deathRuneCount;
	private final int bloodRuneCount;
	private final int soulRuneCount;
	private final int wrathRuneCount;
	private final int bananaCount;

}
