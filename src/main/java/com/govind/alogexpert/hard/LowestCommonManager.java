package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonManager {
	public static OrgChart getLowestCommonManager(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
		return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
	}

	public static OrgInfo getOrgInfo(OrgChart manager, OrgChart reportOne, OrgChart reportTwo) {
		 int numImportantReport=0;
		 for(OrgChart directReport:manager.directReports) {
			 OrgInfo orgInfo=getOrgInfo(directReport, reportOne, reportTwo);
			 if(orgInfo.lowestCommonManager!=null) return orgInfo;
			 numImportantReport+=orgInfo.numImportantReport;
		 }
		 if(manager==reportOne || manager==reportTwo) numImportantReport++;
		 OrgChart lowestCommonManager = numImportantReport==2?manager:null;
		 OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager, numImportantReport);
		 return newOrgInfo;
	}

	static class OrgChart {
		public char name;
		public List<OrgChart> directReports;

		OrgChart(char name) {
			this.name = name;
			this.directReports = new ArrayList<OrgChart>();
		}

		// This method is for testing only.
		public void addDirectReports(OrgChart[] directReports) {
			for (OrgChart directReport : directReports) {
				this.directReports.add(directReport);
			}
		}
	}

	static class OrgInfo {
		public OrgChart lowestCommonManager;
		int numImportantReport;

		OrgInfo(OrgChart lowestCommonManager, int numImportantReport) {
			this.lowestCommonManager = lowestCommonManager;
			this.numImportantReport = numImportantReport;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
