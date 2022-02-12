package Card_Fin_Demo.ms0222.controllers;

import java.util.HashMap;
import java.util.Map;

import Card_Fin_Demo.ms0222.entities.RenTool;
import Card_Fin_Demo.ms0222.entities.RenTool.Brand;
import Card_Fin_Demo.ms0222.entities.RenTool.ToolType;

/**
 * This class acts as a controller for any and all available rental options.
 * 
 * @author matthew stephens
 *
 */
public abstract class RenToolOptionsController {

	private static Map<String, RenTool> availableOptions;

	/**
	 * This method exists to simulate options that would normally be available on a
	 * Database.
	 */
	private static void initAvailableOptions() {

		availableOptions = new HashMap<>();

		RenTool chainSaw = new RenTool("CHNS", ToolType.CHAINSAW, Brand.STIHL);
		RenTool ladder = new RenTool("LADW", ToolType.LADDER, Brand.WERNER);
		RenTool jackhammer1 = new RenTool("JAKD", ToolType.JACKHAMMER, Brand.DEWALT);
		RenTool jackhammer2 = new RenTool("JAKR", ToolType.JACKHAMMER, Brand.RIDGID);

		availableOptions.put(chainSaw.getToolCode(), chainSaw);
		availableOptions.put(ladder.getToolCode(), ladder);
		availableOptions.put(jackhammer1.getToolCode(), jackhammer1);
		availableOptions.put(jackhammer2.getToolCode(), jackhammer2);

	}

	/**
	 * A Singleton instance that returns a copy of the Map to protect the original
	 * Available Options
	 * 
	 * @return
	 */
	public static Map<String, RenTool> getAvailableOptions() {
		if (availableOptions == null) {
			initAvailableOptions();
		}

		return new HashMap<>(availableOptions);
	}

	/**
	 * This method made private because it exists only to illustrate that this would
	 * be an appropriate place to add new options
	 * 
	 * @param newRenTool
	 */
	@SuppressWarnings("unused")
	private static void addOption(RenTool newRenTool) {
		// Do nothing for this demo
	}

	/**
	 * This method made private because it exists only to illustrate that this would
	 * be an appropriate place to remove existing options
	 * 
	 * @param newRenTool
	 */
	@SuppressWarnings("unused")
	private static void removeOption(RenTool newRenTool) {
		// Do nothing for this demo
	}

}
