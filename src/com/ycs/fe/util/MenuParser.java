package com.ycs.fe.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ycs.fe.cache.AppCacheManager;
import com.ycs.fe.dao.UserRoleHelperDAO;
import com.ycs.user.Role;
import com.ycs.user.RoleRightsMap;
import com.ycs.user.Task;

public class MenuParser {
	public static final String menuXml = "MenuXML";
	public String sourceMenuXml = "SourceMenuXml";
	 static {
		 AppCacheManager.createCache(menuXml);
	}
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
		AppCacheManager cache = AppCacheManager.getInstance();
		AppCacheManager.putElementInCache(menuXml, sourceMenuXml, doc);
		cache.getElementFromCache(menuXml, sourceMenuXml);
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

	public static void main(String[] args) {

		UserRoleHelperDAO urh = new UserRoleHelperDAO();
		List<Role> listRole = urh.getRolesForUser("sam_admin");
		List<RoleRightsMap> roletasklist = new ArrayList<RoleRightsMap>();

		for (Role role : listRole) {
			String roleid = role.getRoleId();
			RoleRightsMap roleAndTask = urh.getTaskList(roleid);
			roletasklist.add(roleAndTask);
		}
		System.out.println(roletasklist.toString());
		String xml = new MenuParser().getMenuXml(roletasklist);
	}
}
