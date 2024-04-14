package criv.personalponderings.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class HealthUpdateMixin {

    private ClientWorld world;
    Float oldHealth;
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "onHealthUpdate", at = @At("HEAD"))
    public void onEntityDamage(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        if(client.player == null) return;

        Float newHealth = packet.getHealth();
        if(oldHealth == null) oldHealth = newHealth;

        if(newHealth < oldHealth)
            client.player.sendMessage(Text.of("old: " + oldHealth + " new: " + newHealth + " DIFFERENCE: " + (oldHealth-newHealth)), false);
        oldHealth = newHealth;
            }
        }