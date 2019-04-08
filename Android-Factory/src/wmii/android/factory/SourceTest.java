package wmii.android.factory;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import wmii.android.factory.factoryObject.FactoryObject;
import wmii.android.factory.factoryObject.iFactory.iFactoryOB;
import wmii.android.factory.factoryObject.iFactory.DB.iFactoryDB;
import wmii.android.factory.factoryObject.iFactory.HTML.iFactoryHTML;
import wmii.android.factory.factoryObject.iFactory.JS.iFactoryJS;
import wmii.android.factory.factoryObject.iFactory.XML.iFactoryXML;

public class SourceTest {

	@Test
	public void test() 
	{
		iFactoryOB instanceXML = FactoryObject.getObject("XML");	
		iFactoryOB instanceHTML = FactoryObject.getObject("HTML");
		iFactoryOB instanceJS = FactoryObject.getObject("JS");
		iFactoryOB instanceDB = FactoryObject.getObject("DB");
		
		Assert.assertTrue(instanceXML instanceof iFactoryXML);
		Assert.assertTrue(instanceHTML instanceof iFactoryHTML);
		Assert.assertTrue(instanceJS instanceof iFactoryJS);
		Assert.assertTrue(instanceDB instanceof iFactoryDB);	
	}

}
