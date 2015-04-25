package com.magnet.mmx.selenium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class WizardCustomInstall_TestNGReset {
	 
		public static void main(String[] args) {
	 
			WizardCustomInstall_TestNGReset obj = new WizardCustomInstall_TestNGReset();
	 
            //			String domainName = "magnet.com";
	 
			//in mac oxs
			// String command1 = "ping -c 3 " + domainName;
	        String command1 = "echo; echo Stopping MMX Standalone Server...; killall node app ;kill -9 $(lsof -i:9090 -t)";
	        
	        String command2 = "echo;"
										+"Stopping MMX Standalone Server...;"
										+"killall node app;"
										+"kill -9 $(lsof -i:9090 -t);"
										
										+"echo;"
										+"echo Removing MMX Standalone Server...;"
//										
										
										+"echo;echo dropping db magnetmessagedb;"
										+"mysql -uroot -e \"drop database if exists magnetmessagedb\";"
										+ "sleep 2;"
										
										
										+"echo;"
										+"echo Copying mmx standalone server...;"
										+"curl -u daniel.gulko:dgulko4102 -o mmx-standalone-dist.zip"
										+"http://build.magnet.com:8082/view/MMX/view/MMX%20Develop/job/mmx-develop-all-maven/"
										+"lastSuccessfulBuild/artifact/tools/mmx-standalone-dist/target/mmx-standalone-dist.zip;"
										
//										# unzip standalone server
										+"echo;"
										+"echo extracting mmx-standalone-dist.zip;"
										+"unzip -o mmx-standalone-dist.zip;"
										+"sleep 2;"
										
//										# remove zip package
										+"rm -rf mmx-standalone-dist.zip;"
										+"sleep 2;"
										
//										# Start mmx standalone server
										+"if [[ \"$?\" = 0 ]]; then;"
										+"	echo;"
										+"	echo Starting mmx standalone server...;"
										+"	cd mmx-standalone-dist;"
										+"	./mmx.sh start;"
										+"	sleep 5;"
										+"fi;"
										
										
										+ "echo;"
										+ "MMX Standalone Server Running...";
			// //in windows
			// //String command1 = "ping -n 3 " + domainName;
	 
		     String output1 = obj.executecommand1(command1);
		     String output2 = obj.executecommand2(command2);
	 
	 	     System.out.println(output1);
	 	     System.out.println(output2);
	 
		}
	 
		private String executecommand1(String command1) {
	 
			StringBuffer output = new StringBuffer();
	 
			Process p;
			try {
				p = Runtime.getRuntime().exec(command1);
				p.waitFor();
				BufferedReader reader = 
	                            new BufferedReader(new InputStreamReader(p.getInputStream()));
	 
	                        String line = "";			
				while ((line = reader.readLine())!= null) {
					output.append(line + "\n");
				}
	 
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
			return output.toString();
	 
		}
		
		private String executecommand2(String command2) {
			 
			StringBuffer output = new StringBuffer();
	 
			Process p;
			try {
				p = Runtime.getRuntime().exec(command2);
				p.waitFor();
				BufferedReader reader = 
	                            new BufferedReader(new InputStreamReader(p.getInputStream()));
	 
	                        String line = "";			
				while ((line = reader.readLine())!= null) {
					output.append(line + "\n");
				}
	 
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
			return output.toString();
	 
		}
	 
	}

