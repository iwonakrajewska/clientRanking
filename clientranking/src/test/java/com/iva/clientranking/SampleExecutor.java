package com.iva.clientranking;

public class SampleExecutor {

	public static String[] sampleFiles1 =
            new String[]{"files/test1/c1.csv", "files/test1/a1.csv", "files/test1/s1.csv", "files/test1/p1.csv"};
	
	public static String[] sampleFiles2 =
            new String[]{"files/clients.csv", "files/appointments.csv", "files/services.csv", "files/purchases.csv"};

    public static void main(String[] args) {
        ClientRankingApplication.main(sampleFiles2);
    }

}
