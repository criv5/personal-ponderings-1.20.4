package criv.personalponderings.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
    public class EntityDamageMixin {

        @Shadow
        private ClientWorld world;

        @Inject(method = "onEntityDamage", at = @At("HEAD"))
        public void onEntityDamage(EntityDamageS2CPacket packet, CallbackInfo ci) {
            MinecraftClient client = MinecraftClient.getInstance();
            Entity damagedEntity = world.getEntityById(packet.entityId());
            Entity sourceEntity = world.getEntityById(packet.sourceCauseId());
            if (damagedEntity != null && damagedEntity == MinecraftClient.getInstance().player && sourceEntity != null) {
                    client.player.sendMessage(Text.of("You were damaged by " + sourceEntity + " let me check for damage type maybe its "), false);
            }
        }
    }
