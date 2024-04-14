package criv.personalponderings;

import criv.personalponderings.mixin.client.CheckHealthMixin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.LinkedHashMap;

public class PersonalPonderingsClient implements ClientModInitializer {

	public static LinkedHashMap<Long, Float> tickMap = new LinkedHashMap<>();

	MinecraftClient client = MinecraftClient.getInstance();

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if ((client.world != null) && (client.player != null)) {
				long tick = client.world.getTickOrder();
				float health = client.player.getHealth();
				tickMap.put(tick, health);
				//debugging just to check stuff
				client.player.sendMessage(Text.of(client.world.getTickOrder() + " + " + tickMap.get(tick).toString()), false);
			}
		});
	}
}