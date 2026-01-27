package net.raccoon.will.structura.api.feature;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Hitscan {

    public record HitscanResult(HitType type, BlockHitResult blockHit, EntityHitResult entityHit) {
            public enum HitType {BLOCK, ENTITY, MISS}

            public static HitscanResult block(BlockHitResult blockHit) {
                return new HitscanResult(HitType.BLOCK, blockHit, null);
            }

            public static HitscanResult entity(EntityHitResult entityHit) {
                return new HitscanResult(HitType.ENTITY, null, entityHit);
            }

            public static HitscanResult miss() {
                return new HitscanResult(HitType.MISS, null, null);
            }
        }

    public static HitscanResult performHitscan(Player player, double maxDistance) {
        Level level = player.level();
        Vec3 eyePos = player.getEyePosition(1f);
        Vec3 lookVec = player.getLookAngle();
        Vec3 reachPos = eyePos.add(lookVec.scale(maxDistance));

        //raytrace block first
        BlockHitResult blockHit = level.clip(new ClipContext(
                eyePos,
                reachPos,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));
        double blockDist = blockHit.getType() == HitResult.Type.MISS ? Double.MAX_VALUE
                : blockHit.getLocation().distanceTo(eyePos);

        //check entities
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level,
                player,
                eyePos,
                reachPos,
                player.getBoundingBox().expandTowards(lookVec.scale(maxDistance)).inflate(1.0),
                e -> e instanceof LivingEntity && e != player,
                1f
        );
        double entityDist = entityHit == null ? Double.MAX_VALUE : entityHit.getLocation().distanceTo(eyePos);

        //returns closest hit
        if (entityDist < blockDist) return HitscanResult.entity(entityHit);
        if (blockDist < Double.MAX_VALUE) return HitscanResult.block(blockHit);
        return HitscanResult.miss();
    }
}

