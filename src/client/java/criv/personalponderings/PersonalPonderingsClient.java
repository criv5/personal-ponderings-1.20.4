package criv.personalponderings;

import criv.personalponderings.sound.Sounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.LinkedHashMap;

public class PersonalPonderingsClient implements ClientModInitializer {

	public static LinkedHashMap<Long, Float> tickMap = new LinkedHashMap<>();

	MinecraftClient client = MinecraftClient.getInstance();

	@Override
	public void onInitializeClient() {
		Sounds.registerSounds();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
		});
	}
}