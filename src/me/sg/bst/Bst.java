package me.sg.bst;

import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Bst extends JavaPlugin implements Listener , CommandExecutor {

    File file = new File(this.getDataFolder(),"BetterStaffTeamLog.txt");

    @Override
    public void onEnable(){
        System.out.println("[BetterStaff] BetterStaff enabled");
        System.out.println("[BetterStaff] Enjoying BetterStaff? Join our discord:");
        System.out.println("[BetterStaff] Discord: https://discord.gg/ywKqThFrMu");
        getServer().getPluginManager().registerEvents(this, this);

        this.saveDefaultConfig();
    }


    public static Map<UUID, Location> loc = new HashMap<UUID, Location>();


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){


        if(checkOP(e.getPlayer())){
            String reason = e.getPlayer().getName() +" left the server";
            Location LocalLoc = loc.get(e.getPlayer().getUniqueId());
            e.getPlayer().teleport(LocalLoc);
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            try {
                writeToFile(reason);
            } catch (IOException exception) {
                System.out.println("An error occured");
                exception.printStackTrace();
            }
        }



    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        if(checkOP(e.getPlayer())){
            String reason = e.getPlayer().getName() +" joined the server";
            try {
                writeToFile(reason);
            } catch (IOException exception) {
                System.out.println("An error occured");
                exception.printStackTrace();
            }

        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) throws ClassCastException , CommandException, ArrayIndexOutOfBoundsException {






        if (sender.isOp() || sender instanceof ConsoleCommandSender) {


            if (cmd.getName().equalsIgnoreCase("makeop")) {
                if(args.length!=1){
                    sender.sendMessage("incorrect number of arguments usage: /makeop playerName");
                    return true;

                }

                try{
                    makeOP(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                }
                catch(NullPointerException exception){
                    makeOP(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString());
                }

                sender.sendMessage(args[0]+" is now a server operator");

                String reason = sender.getName() + " opped " + args[0];
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }


            if (cmd.getName().equalsIgnoreCase("svanishallow")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /svanishallow");
                    return true;

                }
                this.getConfig().set("svanish", "allow");
                this.saveConfig();
                sender.sendMessage("operators can now vanish");

                String reason = sender.getName() + " allowed /svanish";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;

            }
            if (cmd.getName().equalsIgnoreCase("svanishdeny")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /svanishdeny");
                    return true;

                }
                this.getConfig().set("svanish", "deny");
                this.saveConfig();
                sender.sendMessage("operators can no longer vanish");

                String reason = sender.getName() + " disabled /svanish";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;

            }

            if (cmd.getName().equalsIgnoreCase("bannallow")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /bannallow");
                    return true;

                }
                this.getConfig().set("ban", "allow");
                this.saveConfig();
                sender.sendMessage("operators can now ban players");

                String reason = sender.getName() + " allowed /bann";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;

            }
            if (cmd.getName().equalsIgnoreCase("banndeny")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /banndeny");
                    return true;

                }
                this.getConfig().set("ban", "deny");
                this.saveConfig();
                sender.sendMessage("operators can no longer ban players");

                String reason = sender.getName() + " disabled /bann";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;

            }
            if (cmd.getName().equalsIgnoreCase("kickkallow")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /kickkallow");
                    System.out.println(args.length);
                    return true;

                }
                this.getConfig().set("kick", "allow");
                this.saveConfig();
                sender.sendMessage("operators can now kick players");

                String reason = sender.getName() + " allowed /kickk";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("kickkdeny")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /kickdeny");
                    return true;

                }
                this.getConfig().set("kick", "deny");
                this.saveConfig();
                sender.sendMessage("operators can no longer kick players");

                String reason = sender.getName() + " disabled /kickk";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;

            }
            if (cmd.getName().equalsIgnoreCase("telepallow")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /telepallow");
                    return true;

                }
                this.getConfig().set("telep", "allow");
                this.saveConfig();
                sender.sendMessage("operators can now teleport");

                String reason = sender.getName() + " allowed /telep";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("telepdeny")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /telepdeny");
                    return true;

                }
                this.getConfig().set("telep", "deny");
                this.saveConfig();
                sender.sendMessage("operators can no longer teleport");

                String reason = sender.getName() + " disabled /telep";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;

            }
            if (cmd.getName().equalsIgnoreCase("spectateallow")) {
                if(args.length!=1){
                    sender.sendMessage("incorrect number of arguments usage: /spectateallow");
                    return true;

                }
                this.getConfig().set("spectate", "allow");
                this.saveConfig();
                sender.sendMessage("Operators can now go in spectator mode");

                String reason = sender.getName() + " allowed /spectate";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("spectatedeny")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /spectatedeny");
                    return true;

                }
                this.getConfig().set("spectate", "deny");
                this.saveConfig();
                sender.sendMessage("Operators can no longer go in spectator mode");

                String reason = sender.getName() + " disabled /spectate";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("survivalallow")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /survivalallow");
                    return true;

                }
                this.getConfig().set("survival", "allow");
                this.saveConfig();
                sender.sendMessage("Operators can now go in survival mode");

                String reason = sender.getName() + " allowed /survival";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("survivaldeny")) {
                if(args.length!=0){
                    sender.sendMessage("incorrect number of arguments usage: /survivaldenyn");
                    return true;

                }
                this.getConfig().set("survival", "deny");
                this.saveConfig();
                sender.sendMessage("Operators can no longer go in survival mode");

                String reason = sender.getName() + " disabled /survival";
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if(cmd.getName().equalsIgnoreCase("removeOP")){

                if(args.length!=1){
                    sender.sendMessage("incorrect number of arguments usage: /removeop playerName");
                    return true;

                }
                try{
                    removeOP(Bukkit.getPlayer(args[0]), sender);
                }catch(NullPointerException exception){
                    removeOP(Bukkit.getOfflinePlayer(args[1]), sender);
                }




                return true;
            }
            if (cmd.getName().equalsIgnoreCase("svanish")) {
                if(args.length!=1){
                    sender.sendMessage("incorrect number of arguments usage: /svanish playerName");
                    return true;

                }


                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + args[1]);
                String reason = sender.getName() + " vanished " +args[1];
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }

                return true;
            }
            if (cmd.getName().equalsIgnoreCase("bann")) {
                if(args.length!=2){
                    sender.sendMessage("incorrect number of arguments usage: /bann playerName Reason");
                    return true;

                }


                try{
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + args[0] + " " + args[1]);
                }catch(ArrayIndexOutOfBoundsException exception){
                    sender.sendMessage("please enter a reason for the ban");

                }catch(NullPointerException exception){
                    sender.sendMessage("This player does not exist");
                }



                String reason = sender.getName() + " banned " + args[0] + " reason : " + args[1];
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if (cmd.getName().equalsIgnoreCase("pardonn")) {
                if(args.length!=1){
                    sender.sendMessage("incorrect number of arguments usage: /pardonn playerName");
                    return true;

                }

                Player bannedPlayer = Bukkit.getPlayer(args[0]);

                try{
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pardon " + bannedPlayer.getName());
                }catch(NullPointerException exception){
                    sender.sendMessage("This player does not exist");

                }



                String reason = sender.getName() + " unbanned " + args[0] ;
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }

                return true;
            }
            if (cmd.getName().equalsIgnoreCase("kickk")) {
                if(args.length!=2){
                    sender.sendMessage("incorrect number of arguments usage: /kick playerName Reason");
                    return true;

                }


                try{
                    Bukkit.getPlayer(args[0]).kickPlayer(args[1]);
                }catch(NullPointerException exception){
                    sender.sendMessage("This player does not exist");

                }

                String reason = sender.getName() + " kicked " + args[0] + " reason : " + args[1];
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return true;
            }
            if(cmd.getName().equalsIgnoreCase("checkop")){
                if(args.length!=1){
                    sender.sendMessage("incorrect number of arguments usage: /checkop playerName");
                    return true;

                }

                boolean b= false;

                try{
                    b=checkOP(Bukkit.getPlayer(args[0]));
                }catch(NullPointerException exception){
                    if(checkOP(Bukkit.getOfflinePlayer(args[1]))){
                        sender.sendMessage("this player is a server operator");
                    }
                    sender.sendMessage("This player does not exist");

                }


                if(b){
                    sender.sendMessage("this player is a server operator");
                }
                else{
                    sender.sendMessage("this player is NOT a server operator");
                }
            }

        }



        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (checkOP(player)) {

                if (cmd.getName().equalsIgnoreCase("spectate")) {
                    if(args.length!=0){
                        sender.sendMessage("incorrect number of arguments usage: /spectate");
                        return true;

                    }
                    if (!checkAllow(cmd.getName())) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }
                    loc.put(player.getUniqueId(), player.getLocation());
                    player.setGameMode(GameMode.SPECTATOR);

                    String reason = player.getName() + " went into spectator mode";
                    try {
                        writeToFile(reason);

                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }

                    return true;

                }
                if (cmd.getName().equalsIgnoreCase("survival")) {
                    if(args.length!=0){
                        sender.sendMessage("incorrect number of arguments usage: /survival");
                        return true;

                    }
                    if (!checkAllow(cmd.getName())) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }
                    try {
                        player.teleport(loc.get(player.getUniqueId()));
                    } catch (IllegalArgumentException exception) {

                    }

                    player.setGameMode(GameMode.SURVIVAL);

                    String reason = player.getName() + " went into survival mode";
                    try {
                        writeToFile(reason);
                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }
                    return true;

                }
                if (cmd.getName().equalsIgnoreCase("telep")) {
                    if(args.length!=1){
                        sender.sendMessage("incorrect number of arguments usage: /telep playerName");
                        return true;

                    }
                    if (!checkAllow(cmd.getName())) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }
                    loc.put(player.getUniqueId(),player.getLocation());
                    try{
                        Player tpTo = Bukkit.getPlayer(args[0]);
                        player.teleport(tpTo.getLocation());
                        player.setGameMode(GameMode.SPECTATOR);
                    }catch(NullPointerException exception){
                        sender.sendMessage("This player does not exist");

                    }




                    String reason = player.getName() + " teleported to " + args[0];
                    try {
                        writeToFile(reason);
                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }

                    return true;


                }
                if (cmd.getName().equalsIgnoreCase("svanish")) {
                    if(args.length!=0){
                        sender.sendMessage("incorrect number of arguments usage: /svanish");
                        return true;

                    }
                    if (checkAllow(cmd.getName())==false) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + sender.getName());
                    String reason = player.getName() + " vanished";
                    try {
                        writeToFile(reason);
                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }

                    return true;
                }
                if (cmd.getName().equalsIgnoreCase("bann")) {
                    if(args.length!=2){
                        sender.sendMessage("incorrect number of arguments usage: /bann playerName Reason");
                        return true;

                    }
                    if (!checkAllow(cmd.getName())) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }

                    try{
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + args[0] + " " + args[1]);
                    }catch(ArrayIndexOutOfBoundsException exception){
                        sender.sendMessage("please enter a reason for the ban");

                    }catch(NullPointerException exception){
                        sender.sendMessage("This player does not exist");
                    }



                    String reason = player.getName() + " banned " + args[0] + " reason : " + args[1];
                    try {
                        writeToFile(reason);
                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }
                    return true;
                }
                if (cmd.getName().equalsIgnoreCase("pardonn")) {
                    if(args.length!=1){
                        sender.sendMessage("incorrect number of arguments usage: /pardonn playerName");
                        return true;

                    }
                    if (!checkAllow(cmd.getName())) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }


                    Player bannedPlayer = Bukkit.getPlayer(args[0]);

                    try{
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pardon " + bannedPlayer.getName());
                    }catch(NullPointerException exception){
                        sender.sendMessage("This player does not exist");

                    }



                    String reason = player.getName() + " unbanned " + args[0] ;
                    try {
                        writeToFile(reason);
                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }

                    return true;
                }
                if (cmd.getName().equalsIgnoreCase("kickk")) {
                    if(args.length!=2){
                        sender.sendMessage("incorrect number of arguments usage: /kick playerName Reason");
                        return true;

                    }
                    if (!checkAllow(cmd.getName())) {
                        player.sendMessage("this option is not avaliable");
                        return false;
                    }

                    try{
                        Bukkit.getPlayer(args[0]).kickPlayer(args[1]);
                    }catch(NullPointerException exception){
                        sender.sendMessage("This player does not exist");

                    }

                    String reason = player.getName() + " kicked " + args[0] + " reason : " + args[1];
                    try {
                        writeToFile(reason);
                    } catch (IOException e) {
                        System.out.println("An error occured");
                        e.printStackTrace();
                    }
                    return true;
                }
                if(cmd.getName().equalsIgnoreCase("checkop")){
                    if(args.length!=1){
                        sender.sendMessage("incorrect number of arguments usage: /checkop playerName");
                        return true;

                    }

                    boolean b= false;

                    try{
                        b=checkOP(Bukkit.getPlayer(args[0]));
                    }catch(NullPointerException exception){
                        if(checkOP(Bukkit.getOfflinePlayer(args[1]))){
                            sender.sendMessage("this player is a server operator");
                        }
                        sender.sendMessage("This player does not exist");

                    }


                    if(b){
                        sender.sendMessage("this player is a server operator");
                    }
                    else{
                        sender.sendMessage("this player is NOT a server operator");
                    }
                }

            }


            return true;
        }


        return true;
    }






    public boolean checkOP(Player player) throws NullPointerException{


        int num = this.getConfig().getStringList("OPs").size();
        List<String> operators = getConfig().getStringList("OPs");
        int count = 0;
        while(count < operators.size()){
            if(operators.get(count).equals(player.getUniqueId().toString())){

                return true;
            }
            count++;
        }
       return false;

    }
    public boolean checkOP(OfflinePlayer player) throws NullPointerException{


        int num = this.getConfig().getStringList("OPs").size();
        List<String> operators = getConfig().getStringList("OPs");
        int count = 0;
        while(count < operators.size()){
            if(operators.get(count).equals(player.getUniqueId().toString())){

                return true;
            }
            count++;
        }

        return true;

    }

    public boolean checkAllow(String args){
        switch (args){
            case "bann":
                if(this.getConfig().get("ban").equals("allow")){
                    return true;
                }
                return false;
            case "kickk":
                if(this.getConfig().get("kick").equals("allow")){
                    return true;
                }
                return false;
            case "survival":
                if(this.getConfig().get("survival").equals("allow")){

                    return true;
                }
                return false;

            case "spectate":
                if(this.getConfig().get("spectate").equals("allow")){
                    return true;
                }
                return false;
            case "telep":
                if(this.getConfig().get("telep").equals("allow")){
                    return true;
                }
                return false;
            case "svanish":
                if(this.getConfig().get("svanish").equals("allow")){
                    return true;
                }
                return false;
        }

        return false;

    }

    public void writeToFile(String log) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(log);
        pw.close();

    }

    public void removeOP(Player player, CommandSender sender) throws NullPointerException{
        int count =0;
        List<String> preOperators = getConfig().getStringList("OPs");
        if(preOperators.size()==0){
            sender.sendMessage("Player not found");
            return;
        }
        System.out.println(preOperators.size());;
        while(count<preOperators.size()){
            if(preOperators.get(count).equals(player.getUniqueId().toString())){
                Bukkit.broadcastMessage(player.getUniqueId().toString());
                preOperators.set(count, "");
                this.getConfig().set("OPs",preOperators);
                this.saveConfig();
                sender.sendMessage("player " + player.getName()+ " deopped");
                return;

            }
            count++;

        }
        sender.sendMessage("player not found");



    }

    public void removeOP(OfflinePlayer player, CommandSender sender) throws NullPointerException{
        int count =0;
        List<String> preOperators = getConfig().getStringList("OPs");
        if(preOperators.size()==0){
            sender.sendMessage("Player not found");
            return;
        }
        System.out.println(preOperators.size());;
        while(count<preOperators.size()){
            if(preOperators.get(count).equals(player.getUniqueId().toString())){
                Bukkit.broadcastMessage(player.getUniqueId().toString());
                preOperators.set(count, "");
                this.getConfig().set("OPs",preOperators);
                this.saveConfig();
                sender.sendMessage("player " + player.getName()+ " deopped");

                String reason = sender.getName() + " deopped " + player.getName();
                try {
                    writeToFile(reason);
                } catch (IOException e) {
                    System.out.println("An error occured");
                    e.printStackTrace();
                }
                return;

            }
            count++;

        }
        sender.sendMessage("player not found");



    }

    public void makeOP(String op){
        List<String> preOperators = getConfig().getStringList("OPs");
        if(preOperators.size()==0){
            preOperators.add(op);
            this.getConfig().set("OPs",preOperators);
            this.saveConfig();
            return ;
        }
        preOperators.add(op);
        this.getConfig().set("OPs",preOperators);
        this.saveConfig();

    }


}

