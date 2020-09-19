package org.trishinfotech.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.trishinfotech.command.commands.Commands.DecreaseSpeedCommand;
import org.trishinfotech.command.commands.Commands.IncreaseSpeedCommand;
import org.trishinfotech.command.commands.Commands.OffCommand;
import org.trishinfotech.command.commands.Commands.OnCommand;
import org.trishinfotech.command.commands.Commands.TVDecreaseChannelCommand;
import org.trishinfotech.command.commands.Commands.TVDecreaseVolumeCommand;
import org.trishinfotech.command.commands.Commands.TVIncreaseChannelCommand;
import org.trishinfotech.command.commands.Commands.TVIncreaseVolumeCommand;
import org.trishinfotech.command.commands.Commands.TVMuteCommand;
import org.trishinfotech.command.devices.Appliance;
import org.trishinfotech.command.devices.bedroom.BedRoomFan;
import org.trishinfotech.command.devices.bedroom.BedRoomLight;
import org.trishinfotech.command.devices.kitchen.KitchenLight;
import org.trishinfotech.command.devices.kitchen.Microwave;
import org.trishinfotech.command.devices.livingroom.LivingRoomFan;
import org.trishinfotech.command.devices.livingroom.LivingRoomLight;
import org.trishinfotech.command.devices.livingroom.LivingRoomTV;

public class HomeAutomation {

	private Map<Appliance, List<Command>> optionsAvailable = new HashMap<Appliance, List<Command>>();

	private HomeAutomation() {
		Appliance bedRoomFan = new BedRoomFan();
		Appliance bedRoomLight = new BedRoomLight();
		Appliance kitchenLight = new KitchenLight();
		Appliance microwave = new Microwave();
		Appliance livingRoomFan = new LivingRoomFan();
		Appliance livingRoomLight = new LivingRoomLight();
		Appliance livingRoomTV = new LivingRoomTV();
		optionsAvailable.put(bedRoomFan,
				Arrays.asList(new OnCommand("BedRoomFan On", bedRoomFan), new OffCommand("BedRoomFan Off", bedRoomFan),
						new IncreaseSpeedCommand("BedRoomFan Increase", bedRoomFan),
						new DecreaseSpeedCommand("BedRoomFan Decrease", bedRoomFan)));
		optionsAvailable.put(bedRoomLight, Arrays.asList(new OnCommand("BedRoomLight On", bedRoomLight),
				new OffCommand("BedRoomLight On", bedRoomLight)));
		optionsAvailable.put(kitchenLight, Arrays.asList(new OnCommand("KitchenLight On", kitchenLight),
				new OffCommand("KitchenLight Off", kitchenLight)));
		optionsAvailable.put(microwave,
				Arrays.asList(new OnCommand("Microwave On", microwave), new OffCommand("Microwave Off", microwave)));
		optionsAvailable.put(livingRoomFan,
				Arrays.asList(new OnCommand("LivingRoomFan On", livingRoomFan),
						new OffCommand("LivingRoomFan Off", livingRoomFan),
						new IncreaseSpeedCommand("LivingRoomFan Increase", livingRoomFan),
						new DecreaseSpeedCommand("LivingRoomFan Decrease", livingRoomFan)));
		optionsAvailable.put(livingRoomLight, Arrays.asList(new OnCommand("LivingRoomLight On", livingRoomLight),
				new OffCommand("LivingRoomLight Off", livingRoomLight)));
		optionsAvailable.put(livingRoomTV,
				Arrays.asList(new OnCommand("LivingRoomTV On", livingRoomTV),
						new OffCommand("LivingRoomTV Off", livingRoomTV),
						new TVIncreaseVolumeCommand("LivingRoomTV Increase Volume", livingRoomTV),
						new TVIncreaseChannelCommand("LivingRoomTV Decrease Channel", livingRoomTV),
						new TVDecreaseVolumeCommand("LivingRoomTV Decrease Volume", livingRoomTV),
						new TVDecreaseChannelCommand("LivingRoomTV Decrease Channel", livingRoomTV),
						new TVMuteCommand("LivingRoomTV Mute/Unmute", livingRoomTV)));
	}

	public List<String> applianceNames() {
		return optionsAvailable.keySet().stream().map(appliance -> appliance.name()).collect(Collectors.toList());
	}

	public List<Command> applianceCommands(String applianceName) {
		List<Command> commands = new ArrayList<Command>();
		if (applianceName != null && !applianceName.trim().isEmpty()) {
			Optional<Appliance> applianceSelected = optionsAvailable.keySet().stream()
					.filter(appliance -> appliance.name().equalsIgnoreCase(applianceName.trim())).findAny();
			if (applianceSelected.isPresent()) {
				commands.addAll(optionsAvailable.get(applianceSelected.get()));
			}
		}
		return commands;
	}

	public static HomeAutomation INSTANCE = new HomeAutomation();

}
