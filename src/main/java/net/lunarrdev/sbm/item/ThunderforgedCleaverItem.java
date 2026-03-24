package net.lunarrdev.sbm.item;

import net.lunarrdev.sbm.client.ThunderClientEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleTypes;

public class ThunderforgedCleaverItem extends SwordItem {

    private LivingEntity lastTarget;

    public ThunderforgedCleaverItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            this.lastTarget = target;
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {

            if (user.getItemCooldownManager().isCoolingDown(this)) {
                return TypedActionResult.fail(stack);
            }

            if (lastTarget != null && lastTarget.isAlive()) {

                ServerWorld serverWorld = (ServerWorld) world;

                //  Sound
                serverWorld.playSound(
                        null,
                        lastTarget.getBlockPos(),
                        SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                        SoundCategory.WEATHER,
                        5.0f,
                        1.0f
                );

                //  Particles
                serverWorld.spawnParticles(
                        ParticleTypes.CRIT,
                        lastTarget.getX(),
                        lastTarget.getY() + 1,
                        lastTarget.getZ(),
                        40,
                        0.5,
                        0.5,
                        0.5,
                        0.2
                );

                // ⚡ Lightning
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(serverWorld);
                if (lightning != null) {
                    lightning.refreshPositionAfterTeleport(
                            lastTarget.getX(),
                            lastTarget.getY(),
                            lastTarget.getZ()
                    );
                    serverWorld.spawnEntity(lightning);
                }

                //  Damage
                lastTarget.damage(world.getDamageSources().magic(), 50.0f);

                //  Knockback
                lastTarget.addVelocity(0, 0.8, 0);
                lastTarget.velocityModified = true;

                //  Weather (50 seconds thunderstorm)
                serverWorld.setWeather(0, 50 * 20, true, true);

                //  Cooldown (2 minutes)
                user.getItemCooldownManager().set(this, 2400);

                //  Trigger client effects (FOV + shake)
                ThunderClientEffects.trigger(user);
            }
        }

        return TypedActionResult.success(stack);
    }
}