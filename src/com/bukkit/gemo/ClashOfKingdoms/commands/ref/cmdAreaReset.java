package com.bukkit.gemo.ClashOfKingdoms.commands.ref;

import org.bukkit.entity.Player;
import com.bukkit.gemo.ClashOfKingdoms.game.*;
import com.bukkit.gemo.ClashOfKingdoms.*;

public class cmdAreaReset
{
	private static String Description = "Reset the gamearea";
	private static String Syntax = "/cok resetarea";
	private static String[] Examples = {};
	
	// CHECK SYNTAX
	public static boolean checkSyntax(String[] args)
	{
		if(args.length != 1)
		{
			return false;
		}
				
		return true;
	}
		
	// EXECUTE THE COMMAND
	public static void ExecuteCommand(Player player, String[] args)
	{
		// CHECK COMMAND SYNTAX
		if(!checkSyntax(args))
		{
			CoKChatUtils.printWrongSyntax(player, Syntax, Examples);
			return;
		}
		
		// EXECUTE COMMAND
		// IS IN GAME
		if(!CoKCore.isPlayerInAnyGame(player))
		{
			CoKChatUtils.printError(player, "You are not in a game!");
			return;
		}
		
		// IS REFEREE
		CoKGame game = CoKCore.getGameByPlayer(player);
		if(!game.isInTeam(player, EnumTeam.REF))
		{
			CoKChatUtils.printError(player, "You are not a referee!");
			return;
		}
		
		// IS GAME ON
		if(game.isGameOn())
		{
			CoKChatUtils.printError(player, "Game is already running!");
			return;
		}
		
		int count = CoKGameArea.reloadArea(game.currentSettings.getSettingsName());
		if(count > 0)
		{
			CoKChatUtils.printSuccess(player, "Gamearea resetted! ( " + count + " Blocks )");		
		}
		else if(count == 0)
		{
			CoKChatUtils.printError(player, "Error while resetting gamearea!");	
		}
		else if(count == -1)
		{
			CoKChatUtils.printError(player, "No gamearea defined!");	
		}
		return;		
	}
	
	public static void setDescription(String description) {
		Description = description;
	}

	public static String getDescription() {
		return Description;
	}
	
	public static String getSyntax() {
		return Syntax;
	}

	public static void setSyntax(String syntax) {
		Syntax = syntax;
	}

	public static String[] getExamples() {
		return Examples;
	}

	public static void setExamples(String[] examples) {
		Examples = examples;
	}
}
