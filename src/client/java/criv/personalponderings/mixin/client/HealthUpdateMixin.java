package criv.personalponderings.mixin.client;

import criv.personalponderings.sound.Sounds;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class HealthUpdateMixin {

    Float oldHealth;
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "onHealthUpdate", at = @At("TAIL"))
    public void onEntityDamage(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        if(client.player == null) return;
        Float newHealth = packet.getHealth();
        if(oldHealth == null) {
            client.player.playSound(Sounds.SPAWN, SoundCategory.MASTER, 1.5F, (float) (Math.random() * 0.2+0.9));
            oldHealth = newHealth;
        }
        float difference = oldHealth-newHealth;

        if(newHealth < oldHealth) {
            client.player.sendMessage(Text.of("old: " + oldHealth + " new: " + newHealth + " DIFFERENCE: " + (difference)), false);
            if(newHealth == 0) {
                client.player.playSound(Sounds.DEATH, SoundCategory.MASTER, 1F, (float) (Math.random() * 0.2 + 0.9));
                return;
            }
            else if (newHealth == 20) client.player.playSound(Sounds.SPAWN, SoundCategory.MASTER, 2F, (float) (Math.random() * 0.2+0.9));
            else if (difference > 8)
                client.player.playSound(Sounds.LARGE_HURT, SoundCategory.MASTER, 1F, (float) (Math.random() * 0.2+0.9));
            else if (difference > 3)
                client.player.playSound(Sounds.MEDIUM_HURT, SoundCategory.MASTER, 1F, (float) (Math.random() * 0.2+0.9));
            else if (difference <= 3)
                if(client.player.isOnFire())
                    client.player.playSound(Sounds.FIRE_HURT, SoundCategory.MASTER, 1F, (float) (Math.random() * 0.2+0.9));
                else if (client.player.getBlockStateAtPos().getBlock() instanceof SweetBerryBushBlock || client.player.getBlockStateAtPos().getBlock() instanceof CactusBlock)
                    client.player.playSound(Sounds.POKE_HURT, SoundCategory.MASTER, 1F, (float) (Math.random() * 0.2+0.9));
                else client.player.playSound(Sounds.SMALL_HURT, SoundCategory.MASTER, 1F, (float) (Math.random() * 0.2+0.9));
        }
        oldHealth = newHealth;
            }
        }