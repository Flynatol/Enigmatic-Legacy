package com.integral.enigmaticlegacy.handlers;

import org.lwjgl.glfw.GLFW;

import com.integral.enigmaticlegacy.EnigmaticLegacy;
import com.integral.enigmaticlegacy.packets.server.PacketEnderRingKey;
import com.integral.enigmaticlegacy.packets.server.PacketSpellstoneKey;
import com.integral.enigmaticlegacy.packets.server.PacketXPScrollKey;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * Class for creating and handling keybinds on the client.
 * @author Integral
 */

public class EnigmaticKeybindHandler {

	@OnlyIn(Dist.CLIENT)
	public static boolean checkVariable;

	public KeyBinding enderRingKey;
	public KeyBinding spellstoneAbilityKey;
	public KeyBinding xpScrollKey;

	@OnlyIn(Dist.CLIENT)
	public void registerKeybinds() {
		this.enderRingKey = new KeyBinding("key.enderRing", GLFW.GLFW_KEY_I, "key.categories.enigmaticLegacy");
		this.spellstoneAbilityKey = new KeyBinding("key.spellstoneAbility", GLFW.GLFW_KEY_K, "key.categories.enigmaticLegacy");
		this.xpScrollKey = new KeyBinding("key.xpScroll", GLFW.GLFW_KEY_J, "key.categories.enigmaticLegacy");

		ClientRegistry.registerKeyBinding(this.enderRingKey);
		ClientRegistry.registerKeyBinding(this.spellstoneAbilityKey);
		ClientRegistry.registerKeyBinding(this.xpScrollKey);

	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onKeyInput(TickEvent.ClientTickEvent event) {

		if (event.phase != TickEvent.Phase.END || !Minecraft.getInstance().isGameFocused()) {
		      return;
		}

		if (this.enderRingKey.isPressed()) {
			if (Minecraft.getInstance().isGameFocused())
				EnigmaticLegacy.packetInstance.send(PacketDistributor.SERVER.noArg(), new PacketEnderRingKey(true));
		}

		if (this.xpScrollKey.isPressed()) {
			EnigmaticLegacy.packetInstance.send(PacketDistributor.SERVER.noArg(), new PacketXPScrollKey(true));
		}

		if (this.spellstoneAbilityKey.isKeyDown() && SuperpositionHandler.hasCurio(Minecraft.getInstance().player, EnigmaticLegacy.enigmaticItem)) {
			EnigmaticLegacy.packetInstance.send(PacketDistributor.SERVER.noArg(), new PacketSpellstoneKey(true));
		} else if (this.spellstoneAbilityKey.isPressed() && SuperpositionHandler.hasSpellstone(Minecraft.getInstance().player)) {
			EnigmaticLegacy.packetInstance.send(PacketDistributor.SERVER.noArg(), new PacketSpellstoneKey(true));
		}



	}
}
