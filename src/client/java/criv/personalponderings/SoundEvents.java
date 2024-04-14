package criv.personalponderings;

import criv.personalponderings.sound.Sounds;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;

public class SoundEvents {
    public static void healthUpdate(Float oldHealth, Float newHealth, MinecraftClient client) {
        if(client.player == null) return;
        if(newHealth == 0)
            playSound(client, Sounds.DEATH, 2F);
        if(oldHealth == null) {
            playSound(client, Sounds.SPAWN, 2F);
            return;
        }

        if((newHealth < oldHealth)&&(newHealth != 0)) {
            switch (Math.round(oldHealth - newHealth)) {
                case 0:
                case 1:
                case 2:
                case 3:
                    if(client.player.isOnFire())
                        playSound(client, Sounds.FIRE_HURT, 1F);
                    else if(client.player.isSubmergedInWater())
                        playSound(client, Sounds.DROWN_HURT, 1.5F);
                    else if(client.player.getBlockStateAtPos().getBlock() instanceof SweetBerryBushBlock || client.player.getBlockStateAtPos().getBlock() instanceof CactusBlock)
                        playSound(client, Sounds.POKE_HURT, 1F);
                    else
                        playSound(client, Sounds.SMALL_HURT, 1F);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    playSound(client, Sounds.MEDIUM_HURT, 1F);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                default:
                    playSound(client, Sounds.LARGE_HURT, 1F);
                    break;
            }
        }
    }

    public static void hungerUpdate(int oldHunger, int newHunger, MinecraftClient client) {
        if(client.player == null) return;
        switch (newHunger) {
            case 0: client.player.sendMessage(Text.of("hunger at 0"));
            case 1: client.player.sendMessage(Text.of("hunger at 1"));
            case 2: client.player.sendMessage(Text.of("hunger at 2"));
            case 3:client.player.sendMessage(Text.of("hunger at 3"));
            case 4:client.player.sendMessage(Text.of("hunger at 4"));
            case 5:client.player.sendMessage(Text.of("hunger at 5"));
            case 6:client.player.sendMessage(Text.of("hunger at 6"));
            case 7:client.player.sendMessage(Text.of("hunger at 7"));
            case 8:client.player.sendMessage(Text.of("hunger at 8"));
            case 9:client.player.sendMessage(Text.of("hunger at 9"));
            case 10:client.player.sendMessage(Text.of("hunger at 10"));
            case 11:client.player.sendMessage(Text.of("hunger at 11"));
            case 12:client.player.sendMessage(Text.of("hunger at 12"));
        }
    }

    public static void entityDamage(Entity source, Entity target, MinecraftClient client) {
        if (client.player == null) return;
        if (target == client.player) {
            if (target.isSubmergedInWater()) return;
            if (source.getType() == EntityType.SKELETON)
                playSound(client, Sounds.SKELETON_HURT, 1F);
            if (source.getType() == EntityType.PHANTOM)
                playSound(client, Sounds.PHANTOM_HURT, 1F);
            else
                playSound(client, Sounds.MOB_HURT, 1F);
        }
    }

    private static void playSound(MinecraftClient client, SoundEvent sound, float volume) {
        if(client.player == null) return;
        client.player.playSound(sound, SoundCategory.MASTER, volume, (float) (Math.random() * 0.2 + 0.9));
    }
}
