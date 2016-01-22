package fileupload_jsf;

import java.util.HashMap;

public class Test {
	
    private final static String FALSE = "0";

    private final static String TRUE = "1";
    
    HashMap<String, String> countyMap = new HashMap<String, String>();
    
    HashMap<String, String> siteMap = new HashMap<String, String>();
    
    String sourceAccpet = "65000120";
    
	String sourcePerson = "65000120";
	
	public static void main(String args[]){
		Test test = new Test();
		test.start();
	}
	
	
	public void start(){
		
		countyMap.put("63000", "63000");
		countyMap.put("64000", "64000");
		countyMap.put("65000", "65000");
		countyMap.put("66000", "66000");
		countyMap.put("67000", "67000");
		
		
		siteMap.put("64000121", "64000121");
		siteMap.put("64000122", "64000122");
		siteMap.put("67000120", "64000120");
		
		
		
		String acceptSiteId = sourceAccpet.substring(0, 5);
		String personSiteId = sourcePerson.substring(0,5);
		
		String acceptData = countyMap.get(personSiteId);
		
		String rtn = FALSE;
				
		if(acceptData != null && acceptData.length() > 0){
			rtn = handleCounty(acceptSiteId, personSiteId, acceptData);
		}else{
			rtn = FALSE;
		}
		
		System.out.println("acceptSiteId = " + acceptSiteId + 
				", personSiteId = " + personSiteId + "，是否能辦理 = " + handle(rtn) + "(" + rtn + ")");
		
	}
	
	/**
	 * 處理可辦理轄區內異地受理
	 * @param acceptSite
	 * @param personSite
	 * @param countySite
	 * @return
	 */
	private String handleCounty(String acceptSite, String personSite, String countySite){
		String rtn = FALSE;
		if(personSite.equals(countySite) && acceptSite.equals(countySite)){
			//高雄目前只有鳳山一二可以受理
			if(personSite.equals("64000") && acceptSite.equals("64000")){
				String tmpPerson = siteMap.get(sourcePerson);
				String tmpSite = siteMap.get(sourceAccpet);
				rtn = handlePartSite(tmpSite, tmpPerson);					
			}else{
				rtn = TRUE;	
			}				
		}
		
		return rtn;
	}
	
	
	/**
	 * 處理部分戶所轄區內異地受理
	 * @param acceptSiteId
	 * @param personSiteId
	 * @return
	 */
	private String handlePartSite(String acceptSiteId, String personSiteId){
		String rtn = FALSE;
		if(personSiteId != null && acceptSiteId != null){
			rtn = TRUE;
		}else{
			rtn = FALSE;
		}
		
		return rtn;
	}
	
	private String handle(String str){
		return str.equals(FALSE) ? "否" : "是";
	}
}
