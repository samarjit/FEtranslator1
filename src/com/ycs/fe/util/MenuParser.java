package com.ycs.fe.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;

import com.ycs.fe.cache.AppCacheManager;
import com.ycs.user.RoleRightsMap;
import com.ycs.user.Task;

public class MenuParser {
	public static final String menuXml = "MenuXML";
	public String sourceMenuXml = "SourceMenuXml";
//	 static {
//		 AppCacheManager.createCache(menuXml);
//	}
	public String getMenuXml(List<RoleRightsMap> roletasklist) {
		String menuXml = null;
		try {
			List<Task> tasklist = new ArrayList<Task>();
			List<Task> list = null;
			for (RoleRightsMap roleAndTask : roletasklist) {
				tasklist.addAll(roleAndTask.getTasks());
//				list = roleAndTask.getTasks();
//				for (Task task : list) {
//					String taskId = task.getTaskid();
//					if (contains(tasklist, taskId)) {
//						System.out.println("Contains :" + task.getTaskid());
//						continue;
//					} else {
//						System.out.println("Adds :" + task.getTaskid());
//						tasklist.add(task);
//					}
//				}
			}
			System.out.println("tasklist Length : " + tasklist.size());
			Document doc = getMenuXML();
			Element root = doc.getRootElement();
			for (Iterator tabItr = root.elementIterator("tab"); tabItr.hasNext();) {
				Element tab = (Element) tabItr.next();
				String tabId = tab.attributeValue("id");
				if (contains(tasklist, tabId)) {
					System.out.println("Role comtains :" + tabId);
				} else {
					root.remove(tab);
					System.out.println("Remove from xml :" + tabId);
				}

				for (Iterator menuItr = tab.elementIterator("menu"); menuItr.hasNext();) {
					Element menu = (Element) menuItr.next();
					String menuId = menu.attributeValue("id");
					if (contains(tasklist, menuId)) {
						System.out.println("Role comtains :" + menuId);
					} else {
						tab.remove(menu);
						System.out.println("Remove from xml :" + menuId);
					}

					for (Iterator submenuItr = menu.elementIterator("submenu"); submenuItr.hasNext();) {
						Element submenu = (Element) submenuItr.next();
						String submenuId = submenu.attributeValue("id");
						if (contains(tasklist, submenuId)) {
							System.out.println("Role comtains :" + submenuId);
						} else {
							menu.remove(submenu);
							System.out.println("Remove from xml :" + submenuId);
						}
					}

				}

			}
			menuXml = doc.asXML();
			createMenuFiles(doc);
			System.out.println(doc.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return menuXml;
	}

	private Document getMenuXML() throws DocumentException {
		String xmlpath = ServletActionContext.getServletContext().getRealPath("WEB-INF/classes/map");
		xmlpath = xmlpath + "\\menu.xml";
		Document doc = new SAXReader().read(xmlpath);
//		AppCacheManager cache = AppCacheManager.getInstance();
//		AppCacheManager.putElementInCache(menuXml, sourceMenuXml, doc);
//		cache.getElementFromCache(menuXml, sourceMenuXml);
		return doc;
	}


	private boolean contains(List<Task> tasklist, String taskId) {
		boolean check = false;
		for (Task task : tasklist) {
			String id = task.getTaskid();
			if (id.equals(taskId)) {
				check = true;
				break;
			}
		}
		return check;
	}

	public void createMenuFiles(Document menuxmlDoc){
		try {
//			String xmlpath = "C:\\Eclipse\\workspace\\FEtranslator1\\WebContent\\html\\menu.xml";
//			Document menuxmlDoc = new SAXReader().read(xmlpath);
			List<Node> tablist = menuxmlDoc.selectNodes("//tab");

			String menutempPath = ServletActionContext.getServletContext().getRealPath("/cms/menu_template.html"); //"C:\\Eclipse\\workspace\\FEtranslator1\\WebContent\\cms\\menu_template.html"
			//org.jsoup.nodes.Document menuTemplate = Jsoup.parse(new File(menutempPath), "UTF-8", "");
			
			String menuDir = ServletActionContext.getServletContext().getRealPath("/cms");//"C:\\Eclipse\\workspace\\FEtranslator1\\WebContent\\cms"
			String tophtmlpath = ServletActionContext.getServletContext().getRealPath("/cms/top.html");//"C:\\Eclipse\\workspace\\FEtranslator1\\WebContent\\cms\\top.html";
			File topfile = new File(tophtmlpath);
			org.jsoup.nodes.Document tophtml = Jsoup.parse(topfile, "UTF-8", "");
			org.jsoup.nodes.Element tabhtml = tophtml.getElementById("tabmenu");
			tabhtml.empty();
			tabhtml.append("<div class='hbuttons' ><li class='tabCollection' id='tabCollection'> </li></div>");
			org.jsoup.nodes.Element li = tophtml.getElementById("tabCollection");
			
			for(Node tab: tablist){
				String name = tab.valueOf("@name");
				String id = tab.valueOf("@id");
				String onclick = tab.valueOf("@onclick");
				List<Node> menulist = tab.selectNodes("menu");
				String menufilename = id+"_menu.html";
				li.append("<a href='"+onclick+"' target='mainframe' id='"+id+"' onclick=\"fun(this.id);MM_goToURL('"+menufilename+"');return false\" >"+name+" </a>");
				File menufile = new File(menuDir+"\\"+menufilename);
				//if(!menufile.exists()){
					menufile.createNewFile();
//				}
				org.jsoup.nodes.Document tempDoc = Jsoup.parse(new File(menutempPath), "UTF-8", "");
				org.jsoup.nodes.Element flipmenu = tempDoc.getElementById("flipmenu");
				updateMenu(flipmenu, id, menulist,"menu");
				
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(menufile));
				dos.write(tempDoc.toString().getBytes());
				System.out.println(li.toString());
			}
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(topfile));
			dos.write(tophtml.toString().getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void updateMenu(org.jsoup.nodes.Element ele, String liid, List<Node> list, String type) {
		if(list.size() > 0){
			org.jsoup.nodes.Element ulele;
			if(type.equals("menu")){
				ulele = ele;
			}else{
				String ulid = liid+"_s";
				ele.append("<ul id='"+ulid+"'></ul>");
				ulele = ele.getElementById(ulid);
			}
			for(Node menu:list){
				String name = menu.valueOf("@name");
				String id = menu.valueOf("@id");
				String onclick = menu.valueOf("@onclick");
				ulele.append("<li id='"+id+"'><a href='"+onclick+"' target='mainFrame'>"+name+"</a></li>");
				org.jsoup.nodes.Element liele = ele.getElementById(id);
				List<Node> childlist = menu.selectNodes("submenu");
				updateMenu(liele,id,childlist,"submenu");
			}
		}
	}

	
	
	
	public static void main(String[] args) {

//		UserRoleHelperDAO urh = new UserRoleHelperDAO();
//		List<Role> listRole = urh.getRolesForUser("sam_admin");
//		List<RoleRightsMap> roletasklist = new ArrayList<RoleRightsMap>();
//
//		for (Role role : listRole) {
//			String roleid = role.getRoleId();
//			RoleRightsMap roleAndTask = urh.getTaskList(roleid);
//			roletasklist.add(roleAndTask);
//		}
//		System.out.println(roletasklist.toString());
//		String xml = new MenuParser().getMenuXml(roletasklist);
		
//		MenuParser mm = new MenuParser();
//		mm.createMenuFiles(null);
	}
}
