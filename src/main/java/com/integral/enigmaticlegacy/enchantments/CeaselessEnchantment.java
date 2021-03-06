package com.integral.enigmaticlegacy.enchantments;

import com.integral.enigmaticlegacy.EnigmaticLegacy;
import com.integral.enigmaticlegacy.api.generic.SubscribeConfig;
import com.integral.omniconfig.wrappers.Omniconfig;
import com.integral.omniconfig.wrappers.OmniconfigWrapper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CeaselessEnchantment extends Enchantment {
	public static Omniconfig.BooleanParameter allowNoArrow;

	@SubscribeConfig
	public static void onConfig(OmniconfigWrapper builder) {
		builder.pushPrefix("CeaselessEnchantment");

		allowNoArrow = builder
				.comment("Whether or not crossbows with Ceaseless should be able to shoot basic arrows even if there are none in player's inventory.")
				.getBoolean("AllowNoArrow", true);

		builder.popPrefix();
	}

	public CeaselessEnchantment(final EquipmentSlotType... slots) {
		super(Enchantment.Rarity.RARE, EnchantmentType.CROSSBOW, slots);
		this.setRegistryName(new ResourceLocation(EnigmaticLegacy.MODID, "ceaseless"));
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	protected boolean canApplyTogether(final Enchantment ench) {
		return super.canApplyTogether(ench);
	}

	@Override
	public boolean canApplyAtEnchantingTable(final ItemStack stack) {
		return stack.getItem() instanceof CrossbowItem;
	}

	@Override
	public boolean isTreasureEnchantment() {
		return false;
	}

	@Override
	public boolean isCurse() {
		return false;
	}

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}
}

