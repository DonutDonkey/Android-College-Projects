package wmii.android.factory.factoryObject;

import wmii.android.factory.factoryObject.iFactory.iFactoryOB;
import wmii.android.factory.factoryObject.iFactory.DB.iFactoryDB;
import wmii.android.factory.factoryObject.iFactory.HTML.iFactoryHTML;
import wmii.android.factory.factoryObject.iFactory.JS.iFactoryJS;
import wmii.android.factory.factoryObject.iFactory.XML.iFactoryXML;

public class FactoryObject {
	
	public static iFactoryOB getObject(String criteria)
	{
		 if ("XML".equals(criteria) ) {
			 return new iFactoryXML();
		 } else if("DB".equals(criteria) ) {
			 return new iFactoryDB();
		 }else if("JS".equals(criteria) ) {
			 return new iFactoryJS();
		 }else if("HTML".equals(criteria) ) {
			 return new iFactoryHTML();
		 }
			 
		 throw new IllegalArgumentException();
	}
}
