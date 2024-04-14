package criv.personalponderings.mixin.client;

import criv.personalponderings.SoundEvents;
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
    int oldHunger;
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "onHealthUpdate", at = @At("TAIL"))
    public void onEntityDamage(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        if(client.player == null) return;
        Float newHealth = packet.getHealth();
        int newHunger = packet.getFood();

        SoundEvents.healthUpdate(oldHealth, newHealth, client);
        SoundEvents.hungerUpdate(oldHunger, newHunger, client);

        oldHealth = newHealth;
        oldHunger = newHunger;
            }
        }