package com.BBBY.DataSimulator.helper.emailUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;

import javax.mail.BodyPart;
import javax.mail.Flags;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;

public class EmailUtility {

	private static Properties EMAILPROP = new Properties();
	private static String fileName = "";
	private static Folder inBox;
	private static Session SESSION;
	private static Store STORE;
	private static Message[] MESSAGES;
	private static Multipart multiPart;
	private static BufferedReader buffReader = null;
	private static String attachmentBody = "";
	private static final String saveAttachmentPath = System.getProperty("user.dir") + "/EmailAttachment/";
	private static List<String> emailDetails = new ArrayList<String>();

	private EmailUtility() {

	}

	public static void emailSetProperty() {
		EMAILPROP = new Properties();
		EMAILPROP.put("mail.STORE.protocol", "imaps"); // setting the Imap Protocol
		EMAILPROP.put("mail.imaps.host", "imap.gmail.com"); // setting the host
		EMAILPROP.put("mail.imaps.port", "993"); // setting the port for IMAP
	}

	public static void emailConnect(String emailID, String password) {
		emailSetProperty(); // set the EMAILPROP
		SESSION = Session.getDefaultInstance(EMAILPROP, null); // create SESSION
		try {
			STORE = SESSION.getStore("imaps");
			STORE.connect(emailID, password); // Trying to connect IMAP server
			inBox = STORE.getFolder("inBox"); // Reading inBox Folder
			inBox.open(Folder.READ_WRITE); // Read mode set for email.
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void markAllEmailAsRead(String emailID, String password) {
		emailConnect(emailID, password);
		try {
			inBox.setFlags(inBox.getMessages(), new Flags(Flags.Flag.SEEN), true);
			inBox.close(false);
			STORE.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static boolean isEmailReceived(String emailID, String password, String emailSubject)
			throws MessagingException {
		emailConnect(emailID, password);
		try {
			MESSAGES = inBox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)); // list MESSAGES which are not
																						// read.
			for (int i = 0; i < MESSAGES.length; i++) {
				if (MESSAGES[i].getSubject().contains(emailSubject)) {
					return true;
				}
			}
			return false;
		} finally {
			inBox.close(false);
			STORE.close();
		}
	}
	
	public static List<String> getEmailBodyAndDownloadAttachment(String emailID, String password, String emailSubject) throws MessagingException, IOException
	   {
	      emailConnect(emailID, password);
	      emailDetails.clear();
	      MESSAGES = inBox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
	      for (int i = 0; i < MESSAGES.length; i++)
	      {
	         if (MESSAGES[i].getSubject().contains(emailSubject) && MESSAGES[i].getContentType().contains("multipart"))
	         {
	            downloadAttachment(MESSAGES[i], saveAttachmentPath);
	            emailDetails.add(getTextFromMessage(MESSAGES[i]));
	            return(emailDetails);
	         }
	      }
	      return(emailDetails);
	   }
	
	public static void downloadAttachment(Message message, String saveAttachmentPath) throws IOException, MessagingException
	   {

	      multiPart = (Multipart) message.getContent();
	      int numberOfParts = multiPart.getCount();
	      for (int partCount = 0; partCount < numberOfParts; partCount++)
	      {
	         MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	         if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()))
	         {
	            fileName = part.getFileName();
	            emailDetails.add(fileName);
	            String attachFile = saveAttachmentPath + fileName;
	            part.saveFile(attachFile);
	            emailDetails.add(attachFile);
	         }
	      }
	   }

	   public static String getTextFromMessage(Message message) throws MessagingException, IOException
	   {
	      String result = "";
	      if (message.isMimeType("text/plain"))
	      {
	         result = message.getContent().toString();
	      }
	      else if (message.isMimeType("multipart/*"))
	      {
	         MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	         result = getTextFromMimeMultipart(mimeMultipart);
	      }
	      return result;
	   }

	   public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException
	   {
	      String result = getTextFromMultiMime(mimeMultipart);
	      return result;
	   }

	   public static String getTextFromMultiMime(MimeMultipart mimeMulti) throws MessagingException, IOException
	   {
	      StringBuffer result = new StringBuffer("");
	      for (int i = 0; i < mimeMulti.getCount(); i++)
	      {
	         BodyPart bodyPart = mimeMulti.getBodyPart(i);
	         if (bodyPart.isMimeType("text/plain"))
	         {
	            result.append("\n").append(bodyPart.getContent());
	            break; // without break same text appears twice in my tests
	         }
	         else if (bodyPart.isMimeType("text/html"))
	         {
	            String html = (String) bodyPart.getContent();
	            result.append("\n").append(Jsoup.parse(html).text());
	         }
	         else if (bodyPart.getContent() instanceof MimeMultipart)
	            result.append(getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent()));
	      }
	      return result.toString();
	   }

	   public static String getAttachmentData(String attachmentPath) throws IOException
	   {
	      try
	      {
	         buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(attachmentPath), "UTF-8"));
	         attachmentBody=  readFile(buffReader);
	         return attachmentBody;
	      }

	      finally
	      {
	         buffReader.close();
	      }
	   }

	   public static String readFile(BufferedReader bufReader) throws IOException
	   {
	      String line = "";
	      StringBuffer attachmentData = new StringBuffer("");
	      while ((line = bufReader.readLine()) != null)
	      {
	         String[] data = line.split(",");
	         for (int i = 0; i < data.length; i++)
	         {
	            attachmentData.append(" ").append(data[i]);
	         }
	      }
	      return(attachmentData.substring(1, attachmentData.length()));
	   }

	   public static void deleteFile(String filePath)
	   {
	      File file = new File(filePath);
	      if(file.delete())
	      {
	         System.out.println("File deleted successfully");
	      }
	      else
	         System.out.println("Failed to delete the file");
	   }

}
