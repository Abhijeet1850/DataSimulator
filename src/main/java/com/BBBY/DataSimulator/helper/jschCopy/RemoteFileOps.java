package com.BBBY.DataSimulator.helper.jschCopy;

import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteFileOps {

	private String host;
	private Integer port;
	private String username;
	private String password;

	private JSch jsch;
	private Session activeSession;
	private Channel channel;
	private ChannelSftp sftpChannel;

	public RemoteFileOps(String host, Integer port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public void connect() {
		try {
			jsch = new JSch();
			activeSession = jsch.getSession(username, host, port);
			activeSession.setConfig("StrictHostKeyChecking", "no");
			activeSession.setPassword(password);
			activeSession.connect();

			channel = activeSession.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		System.out.println("disconnecting...");
		sftpChannel.disconnect();
		channel.disconnect();
		activeSession.disconnect();
	}

	public void upload(String fileName, String remoteDir) {

		FileInputStream fis = null;
		connect();
		try {
			sftpChannel.cd(remoteDir);
			File file = new File(fileName);
			fis = new FileInputStream(file);
			sftpChannel.put(fis, file.getName());

			fis.close();
			System.out.println("File uploaded successfully - " + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		disconnect();
	}

}
