package com.integral.enigmaticlegacy.objects;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceNemesisCurse extends EntityDamageSource {

	public DamageSourceNemesisCurse(Entity attacker) {
		super("thorns", attacker);

		this.setMagicDamage();
	}

}
