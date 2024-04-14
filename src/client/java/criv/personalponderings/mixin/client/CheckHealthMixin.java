package criv.personalponderings.mixin.client;

import criv.personalponderings.PersonalPonderingsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static criv.personalponderings.PersonalPonderingsClient.tickMap;

@Mixin(ClientPlayNetworkHandler.class)
public class CheckHealthMixin {

    @Shadow
    private ClientWorld world;

    @Inject(method = "onHealthUpdate", at = @At("HEAD"))
    public void onEntityDamage(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        if(tickMap == null) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            if(tickMap.get(world.getTickOrder() - 1) == null) {
                client.player.sendMessage(Text.of("a tick is missing or something because last tick is null"), false);
                return;
            } else if(tickMap.get(world.getTickOrder() - 1) != null) {
                float oldHealth = tickMap.get(world.getTickOrder() - 1);
                float newHealth = packet.getHealth();
                float difference = oldHealth - newHealth;
                if (difference > 1) {
                    client.player.sendMessage(Text.of("new health update: " + difference), false);
                }
            }
        }
    }
}