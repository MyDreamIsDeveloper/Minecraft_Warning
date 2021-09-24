package com.windows.Warning;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Warning extends JavaPlugin implements Listener {
	
	String prefix = ChatColor.YELLOW + "[WINDOWS] §b";
	private final String USER_AGENT = "Mozilla/5.0";
	private String Address = "http://ipip.kr";
	private URL Url;
	private BufferedReader br;
	private HttpURLConnection con;
	private String protocol = "GET";
	private String IP = null;
	private String Domain = "";
	
	public void onEnable() {
		try {
			Url = new URL(this.Address);
		} catch (MalformedURLException e) {
		}
		try {
			con = (HttpURLConnection)Url.openConnection();
		} catch (IOException e) {
		}
		try {
			con.setRequestMethod(protocol);
		} catch (ProtocolException e) {
		}
		con.setRequestProperty("USER-Agent", USER_AGENT);
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		} catch (IOException e) {
		}
		String line;
		String ip = null;
		try {
			while((line = br.readLine())!= null){
			if (line.startsWith("<title>Your IP is ")){
				ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
			}
			}
		} catch (NullPointerException e1) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Warning System ]");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "인터넷 연결 상태가 올바르지 않습니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
			}
				Bukkit.shutdown();
		} catch (IOException e) {
		}
		try {
			br.close();
		} catch (IOException e) {
		}
  	      try {
	  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
	  	        if (!ip.equalsIgnoreCase(IP)) {
	  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Warning System ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Thread.sleep(10000L);
	  				Bukkit.shutdown();
	  	        }
	  	      }
	  	      catch (UnknownHostException e1) {
		  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Warning System ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
					}
	  				Bukkit.shutdown();
	  	      } catch (InterruptedException e) {
			}
		  BufferedInputStream in = null;
			String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
			StringBuffer sb = new StringBuffer();
			
			try {
				URL url = new URL(strUrl);
				URLConnection urlConnection = url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				
				byte[] bufRead = new byte[40960];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0)
					sb.append(new String(bufRead, 0, lenRead));

			} catch (IOException ioe) {}
			if (sb.toString().contains("[" + Domain + "]")) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Warning System ]");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "해당 플러그인은 현재 차단된 상태이므로 사용하실 수 없습니다.");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {
			}
			Bukkit.shutdown();
			return;
			}
		Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §a경고점수 플러그인 활성화");
		getServer().getPluginManager().registerEvents(this, this);
		File f = new File("plugins/Warning");
		File f2 = new File("plugins/Warning/Data");
		if (!f.exists())
		{
		  f.mkdir();
	    }
		if (!f2.exists())
		{
		  f2.mkdir();
	    }
	}
	
	public void DecompileProtect() {
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		list.stream().filter((Integer num) -> num % 2 == 0);
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §c경고점수 플러그인 비활성화");
	}
	
	  public String readFile(File textFileName) throws IOException {
		    FileReader fr = new FileReader(textFileName);

		    String s = new String();
		    int a;
		    while ((a = fr.read()) != -1)
		    {
		      s = s + (char)a;
		    }
		    fr.close();
		    return s;
		  }
	
	public String getFinalArg(String[] args, int start)
	  {
	    StringBuilder bldr = new StringBuilder();
	    for (int i = start; i < args.length; i++) {
	      if (i != start) {
	        bldr.append(" ");
	      }
	      bldr.append(args[i]);
	    }
	    return bldr.toString();
	  }
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent event) {
		if (getConfig().getInt("경고." + event.getPlayer().getName().toLowerCase()) >= 100) {
				event.disallow(null, "§e[WINDOWS]\n§4경고점수 100점이 초과되어 서버에 입장하실 수 없습니다.");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("경고")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("제품코드") || args[0].equalsIgnoreCase("productcode")) {
					player.sendMessage(ChatColor.DARK_AQUA + "[WINDOWS] " + ChatColor.YELLOW + "0067");
					return false;
				}
			}
			String message = getFinalArg(args, 2);
			if (!sender.hasPermission("warning.use")) {
				sender.sendMessage(prefix + "§c당신은 권한이 없습니다.");
				return false;
			}
			if (args.length <= 2) {
		        sender.sendMessage(prefix + "§c올바르지 않은 명령어. §b/경고 <아이디> <점수> <사유>");
		        return false;
		    }
			if (Integer.parseInt(args[1]) <= 0) {
				sender.sendMessage(prefix + "§c정상적인 값이 아닙니다. §b/경고 <아이디> <점수> <사유>");
		        return false;
			}
			if (!getConfig().contains("경고." + args[0].toLowerCase())) {
				Bukkit.broadcastMessage(prefix + "§4" + args[0] + " §e님은 경고점수 §c" + args[1] + " 점§e이 부과되었습니다.");
				Bukkit.broadcastMessage(prefix + "§c경고 사유: §4" + message);
				Bukkit.broadcastMessage(prefix + "§c처리자: " + player.getDisplayName());
				getConfig().set("경고." + args[0].toLowerCase(), Integer.parseInt(args[1]));
				saveConfig();
				if ((getConfig().getInt("경고." + args[0].toLowerCase()) >= 100) && (Bukkit.getPlayer(args[0]) != null)) {
					Bukkit.getPlayer(args[0]).kickPlayer("§e[WINDOWS]\n§4경고점수 100점이 초과되어 서버에 입장하실 수 없습니다.");
				}
			      File f = new File("plugins/Warning/List.txt");
			      if (!f.exists())
			      {
			        try
			        {
			          FileOutputStream output = new FileOutputStream(f, true);
			          output.flush();
			          output.close();
			        }
			        catch (Exception localException)
			        {
			        }
			      }
			      try
			      {
			        String a = readFile(f);
			        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			        String b = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss", Locale.KOREA).format(new Date());
					bw.write(a + "[" + b + "] " + player.getName() + " 님이 " + args[0] + " 님에게 " + args[1] + " 점 부여\r\n");
					bw.write("사유: " + message + "\r\n");
			        bw.flush();
			        bw.close();
			      }
			      catch (Exception localException1)
			      {
			      }
			      File f2 = new File("plugins/Warning/Data/" + args[0].toLowerCase() + ".txt");
			      if (!f2.exists())
			      {
			        try
			        {
			          FileOutputStream output = new FileOutputStream(f2, true);
			          output.flush();
			          output.close();
			        }
			        catch (Exception localException)
			        {
			        }
			      }
			      try
			      {
			        String a = readFile(f2);
			        BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
			        String b = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss", Locale.KOREA).format(new Date());
					bw.write(a + "§b[" + b + "] §4처리자: " + player.getName() + " / 부여 점수: " + args[1] + " / 사유: " + message + "\r\n");
			        bw.flush();
			        bw.close();
			      }
			      catch (Exception localException1)
			      {
			      }
				return false;
			}
			int getWarn = getConfig().getInt("경고." + args[0].toLowerCase());
			getWarn += Integer.parseInt(args[1]);
			Bukkit.broadcastMessage(prefix + "§4" + args[0] + " §e님은 경고점수 §c" + args[1] + " 점§e이 부과되었습니다.");
			Bukkit.broadcastMessage(prefix + "§c경고 사유: §4" + message);
			Bukkit.broadcastMessage(prefix + "§c처리자: " + player.getDisplayName());
			getConfig().set("경고." + args[0].toLowerCase(), getWarn);
			saveConfig();
			if ((getConfig().getInt("경고." + args[0].toLowerCase()) >= 100) && (Bukkit.getPlayer(args[0]) != null)) {
				Bukkit.getPlayer(args[0]).kickPlayer("§e[WINDOWS]\n§4경고점수 100점이 초과되어 서버에 입장하실 수 없습니다.");
			}
		      File f = new File("plugins/Warning/List.txt");
		      if (!f.exists())
		      {
		        try
		        {
		          FileOutputStream output = new FileOutputStream(f, true);
		          output.flush();
		          output.close();
		        }
		        catch (Exception localException)
		        {
		        }
		      }
		      try
		      {
		        String a = readFile(f);
		        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		        String b = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss", Locale.KOREA).format(new Date());
				bw.write(a + "[" + b + "] " + player.getName() + " 님이 " + args[0] + " 님에게 " + args[1] + " 점 부여\r\n");
				bw.write("사유: " + message + "\r\n");
		        bw.flush();
		        bw.close();
		      }
		      catch (Exception localException1)
		      {
		      }
		      File f2 = new File("plugins/Warning/Data/" + args[0].toLowerCase() + ".txt");
		      if (!f2.exists())
		      {
		        try
		        {
		          FileOutputStream output = new FileOutputStream(f2, true);
		          output.flush();
		          output.close();
		        }
		        catch (Exception localException)
		        {
		        }
		      }
		      try
		      {
		        String a = readFile(f2);
		        BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
		        String b = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss", Locale.KOREA).format(new Date());
				bw.write(a + "§b[" + b + "] §4처리자: " + player.getName() + " / 부여 점수: " + args[1] + " / 사유: " + message + "\r\n");
		        bw.flush();
		        bw.close();
		      }
		      catch (Exception localException1)
		      {
		      }
			return false;
		}
		if (label.equalsIgnoreCase("경고확인")) {
			if (!sender.hasPermission("shine.rudrh")) {
				sender.sendMessage(prefix + "§c당신은 권한이 없습니다.");
				return false;
			}
			if (args.length != 1) {
		        sender.sendMessage(prefix + "§c올바르지 않은 명령어. §b/경고확인 <아이디>");
		        return false;
		    }
			if (!getConfig().contains("경고." + args[0].toLowerCase())) {
				sender.sendMessage(prefix + "§4" + args[0] + " §e님의 경고점수는 §c0 점 §e입니다.");
				return false;
			}
			sender.sendMessage(prefix + "§4" + args[0] + " §e님의 경고점수는 §c" + getConfig().getInt("경고." + args[0].toLowerCase()) + " 점 §e입니다.");
			return false;
		}
		if (label.equalsIgnoreCase("경고점수")) {
			if (!getConfig().contains("경고." + sender.getName().toLowerCase())) {
				sender.sendMessage(prefix + "§e당신의 경고점수는 §c0 점 §e입니다.");
				return false;
			}
			sender.sendMessage(prefix + "§e당신의 경고점수는 §c" + getConfig().getInt("경고." + sender.getName().toLowerCase()) + " 점 §e입니다.");
			return false;
		}
		if (label.equalsIgnoreCase("경고차감")) {
			String message = getFinalArg(args, 2);
			if (!sender.hasPermission("shine.rudrh2")) {
				sender.sendMessage(prefix + "§c당신은 권한이 없습니다.");
				return false;
			}
			if (args.length <= 2) {
		        sender.sendMessage(prefix + "§c올바르지 않은 명령어. §b/경고차감 <아이디> <점수> <사유>");
		        return false;
		    }
			if (Integer.parseInt(args[1]) <= 0) {
				sender.sendMessage(prefix + "§c정상적인 값이 아닙니다. §b/경고차감 <아이디> <점수> <사유>");
		        return false;
			}
			if (!getConfig().contains("경고." + args[0].toLowerCase())) {
				sender.sendMessage(prefix + "해당 유저는 경고점수를 받은적이 없습니다.");
				return false;
			}
			int getWarn = getConfig().getInt("경고." + args[0].toLowerCase());
			getWarn -= Integer.parseInt(args[1]);
			if (getWarn < 0) {
				sender.sendMessage(prefix + "차감하려는 양이 너무 많습니다.");
				return false;
			}
			Bukkit.broadcastMessage(prefix + "§4" + args[0]+ " §e님은 경고점수 §c" + args[1] + " 점§e이 §b차감§e되었습니다.");
			Bukkit.broadcastMessage(prefix + "§b차감 사유: §4" + message);
			Bukkit.broadcastMessage(prefix + "§c처리자: " + player.getDisplayName());
			getConfig().set("경고." + args[0].toLowerCase(), getWarn);
			saveConfig();
		      File f = new File("plugins/Warning/List.txt");
		      if (!f.exists())
		      {
		        try
		        {
		          FileOutputStream output = new FileOutputStream(f, true);
		          output.flush();
		          output.close();
		        }
		        catch (Exception localException)
		        {
		        }
		      }
		      try
		      {
		        String a = readFile(f);
		        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		        String b = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss", Locale.KOREA).format(new Date());
				bw.write(a + "[" + b + "] " + player.getName() + " 님이 " + args[0] + " 님의 경고점수 " + args[1] + " 점 차감\r\n");
				bw.write("사유: " + message + "\r\n");
		        bw.flush();
		        bw.close();
		      }
		      catch (Exception localException1)
		      {
		      }
		      File f2 = new File("plugins/Warning/Data/" + args[0].toLowerCase() + ".txt");
		      if (!f2.exists())
		      {
		        try
		        {
		          FileOutputStream output = new FileOutputStream(f2, true);
		          output.flush();
		          output.close();
		        }
		        catch (Exception localException)
		        {
		        }
		      }
		      try
		      {
		        String a = readFile(f2);
		        BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
		        String b = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss", Locale.KOREA).format(new Date());
				bw.write(a + "§b[" + b + "] §b처리자: " + player.getName() + " / 차감 점수: " + args[1] + " / 사유: " + message + "\r\n");
		        bw.flush();
		        bw.close();
		      }
		      catch (Exception localException1)
		      {
		      }
			return false;
		}
		if (label.equalsIgnoreCase("경고사유")) {
	          File f = new File("plugins/Warning/Data/" + sender.getName().toLowerCase() + ".txt");
	          if (f.exists())
	          {
	            try {
					sender.sendMessage(readFile(f).replaceAll("\r", ""));
				} catch (IOException e) {
				}
	            return false;
	          }
	          else {
	            sender.sendMessage(prefix + "§c당신은 경고를 받은적이 없습니다.");
	            return false;
	          }
	        }
		return true;
	}
	
	  @EventHandler(priority=EventPriority.HIGHEST)
		private void onJoin(PlayerLoginEvent event) {
		  BufferedInputStream in = null;
			String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
			StringBuffer sb = new StringBuffer();
			
			try {
				URL url = new URL(strUrl);
				URLConnection urlConnection = url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				
				byte[] bufRead = new byte[40960];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0)
					sb.append(new String(bufRead, 0, lenRead));

			} catch (IOException ioe) {}
			if (sb.toString().contains("[" + Domain + "]")) {
				event.disallow(null, "§4[WINDOWS]\n§e경고 플러그인이 제작자에 의해 차단되었습니다.");
				for (Player p : Bukkit.getOnlinePlayers()) {
				p.kickPlayer("§4[WINDOWS]\n§e경고 플러그인이 제작자에 의해 차단된 상태입니다.\n차단이 풀리기전까지 해당 플러그인을 사용할 수 없습니다.");
				}
				Bukkit.shutdown();
				} else if (sb.toString().contains("[" + event.getPlayer().getName().toLowerCase() + "]")) {
				event.disallow(null, "§4[WINDOWS] §e해당 아이디는 블랙리스트에 등록된 아이디입니다.");
				}
			try {
				Url = new URL(this.Address);
			} catch (MalformedURLException e) {
			}
			try {
				con = (HttpURLConnection)Url.openConnection();
			} catch (IOException e) {
			}
			try {
				con.setRequestMethod(protocol);
			} catch (ProtocolException e) {
			}
			con.setRequestProperty("USER-Agent", USER_AGENT);
			try {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			} catch (IOException e) {
			}
			String line;
			String ip = null;
			try {
				while((line = br.readLine())!= null){
				if (line.startsWith("<title>Your IP is ")){
					ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
				}
				}
			} catch (IOException e) {
			}
			try {
				br.close();
			} catch (IOException e) {
			}
	  	      try {
		  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
		  	        if (!ip.equalsIgnoreCase(IP)) {
		  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Warning System ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Thread.sleep(10000L);
		  				Bukkit.shutdown();
		  	        }
		  	      }
		  	      catch (UnknownHostException e1) {
			  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Warning System ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				try {
							Thread.sleep(10000L);
						} catch (InterruptedException e) {
						}
		  				Bukkit.shutdown();
		  	      } catch (InterruptedException e) {
				}
		}
}
